参考：https://blog.csdn.net/u012706811/article/details/53231598

 private static Map<String,ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
这个map中的key对应的value只应该有一个。

测试类：com.ls.learn.threadlocal.DateUtil

日志分析
put new sdf of pattern MMddHHmmssSSS to map
thread: Thread[main,5,main] init pattern: MMddHHmmssSSS
thread: Thread[Thread-0,5,main] init pattern: MMddHHmmssSSS
thread: Thread[Thread-1,5,main] init pattern: MMddHHmmssSSS

分析
可以看出来sdfMap put进去了一次,而SimpleDateFormat被new了三次,因为代码中有三个线程.那么这是为什么呢?
对于每一个线程Thread,其内部有一个ThreadLocal.ThreadLocalMap threadLocals的全局变量引用,
ThreadLocal.ThreadLocalMap里面有一个保存该ThreadLocal和对应value,一图胜千言,结构图如下:



那么对于sdfMap的话,结构图就变更了下



那么日志为什么是这样的?分析下:

1.首先第一次执行DateUtil.formatDate(new Date(),MDHMSS);
//第一次执行DateUtil.formatDate(new Date(),MDHMSS)分析
    private static SimpleDateFormat getSdf(final String pattern){
        ThreadLocal<SimpleDateFormat> sdfThread = sdfMap.get(pattern);
        //得到的sdfThread为null,进入if语句
        if (sdfThread == null){
            synchronized (DateUtil.class){
                sdfThread = sdfMap.get(pattern);
                //sdfThread仍然为null,进入if语句
                if (sdfThread == null){
                    //打印日志
                    logger.debug("put new sdf of pattern " + pattern + " to map");
                    //创建ThreadLocal实例,并覆盖initialValue方法
                    sdfThread = new ThreadLocal<SimpleDateFormat>(){
                        @Override
                        protected SimpleDateFormat initialValue() {
                            logger.debug("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    //设置进如sdfMap
                    sdfMap.put(pattern,sdfThread);
                }
            }
        }
        return sdfThread.get();
    }

这个时候可能有人会问,这里并没有调用ThreadLocal的set方法,那么值是怎么设置进入的呢? 
这就需要看sdfThread.get()的实现:

    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }

也就是说当值不存在的时候会调用setInitialValue()方法,该方法会调用initialValue()方法,也就是我们覆盖的方法.

对应日志打印.

put new sdf of pattern MMddHHmmssSSS to map
thread: Thread[main,5,main] init pattern: MMddHHmmssSSS

2.第二次在子线程执行DateUtil.formatDate(new Date(),MDHMSS);
//第二次在子线程执行`DateUtil.formatDate(new Date(),MDHMSS);`
    private static SimpleDateFormat getSdf(final String pattern){
        ThreadLocal<SimpleDateFormat> sdfThread = sdfMap.get(pattern);
        //这里得到的sdfThread不为null,跳过if块（？？？？？？）
        if (sdfThread == null){
            synchronized (DateUtil.class){
                sdfThread = sdfMap.get(pattern);
                if (sdfThread == null){
                    logger.debug("put new sdf of pattern " + pattern + " to map");
                    sdfThread = new ThreadLocal<SimpleDateFormat>(){
                        @Override
                        protected SimpleDateFormat initialValue() {
                            logger.debug("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern,sdfThread);
                }
            }
        }
        //直接调用sdfThread.get()返回
        return sdfThread.get();
    }


分析sdfThread.get()

//第二次在子线程执行`DateUtil.formatDate(new Date(),MDHMSS);`
    public T get() {
        Thread t = Thread.currentThread();//得到当前子线程
        ThreadLocalMap map = getMap(t);
        //子线程中得到的map为null,跳过if块
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        //直接执行初始化,也就是调用我们覆盖的initialValue()方法
        return setInitialValue();
    }

对应日志:

Thread[Thread-0,5,main] init pattern: MMddHHmmssSSS
同理第三次执行和第二次类似.直接调用sdfThread.get(),然后调用initialValue()方法,对应日志

Thread[Thread-1,5,main] init pattern: MMddHHmmssSSS

总结
在什么场景下比较适合使用ThreadLocal？stackoverflow上有人给出了还不错的回答。 
When and how should I use a ThreadLocal variable? 
One possible (and common) use is when you have some object that is not thread-safe, but you want to avoid synchronizing access to that 
object (I’m looking at you, SimpleDateFormat). Instead, give each thread its own instance of the object.



