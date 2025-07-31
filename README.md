
1
要求线程a执行完才开始线程b，线程b执行完才开始线程c
Thread a must finish before thread b starts, and thread b must finish before thread c starts
三个线程按顺序执行
Three threads execute in order
主线程调用a.join()等a结束，再启动b并调用b.join()，最后启动c。
Main thread calls a.join() to wait for a to finish, then starts b and calls b.join(), finally starts c.

2
两个线程轮流打印数字，一直到100
Two threads print numbers alternately up to 100
两个线程交替打印1~100
Two threads alternately print 1~100
共享变量+wait/notify，一个线程打印后修改变量并唤醒另一线程，自己进入等待。
Shared variable + wait/notify, one thread prints and modifies the variable, wakes up the other thread, and then waits itself.

3
写两个线程，一个线程打印152，另一个线程打印AZ，打印顺序是12A34B...5152Z
Write two threads, one prints 152, the other prints AZ, print order is 12A34B...5152Z
数字线程每次打印两个数字，字母线程每次打印一个字母
Number thread prints two numbers each time, letter thread prints one letter each time
数字线程打印两个数字后唤醒字母线程，字母线程打印一个字母后唤醒数字线程，循环26次。
Number thread wakes up letter thread after printing two numbers, letter thread wakes up number thread after printing one letter, loop 26 times.

4
编写一个程序，启动三个线程，三个线程的ID分别是A，B，C；每个线程将自己的ID值在屏幕上打印5遍，打印顺序是ABCABC...
Write a program to start three threads, IDs are A, B, C; each thread prints its ID 5 times, print order is ABCABC...
三个线程按固定顺序循环打印ID
Three threads print IDs in fixed order
共享状态变量，A打印后置为1并唤醒B，B打印后置为2并唤醒C，C打印后置为0并唤醒A，循环5次。
Shared state variable, after A prints set to 1 and wake up B, after B prints set to 2 and wake up C, after C prints set to 0 and wake up A, loop 5 times.

5
编写10个线程，第一个线程从1加到10，第二个线程从11加20…第十个线程从91加到100，最后再把10个线程结果相加
Write 10 threads, first thread sums 1 to 10, second sums 11 to 20... tenth sums 91 to 100, finally sum all results
多线程分段累加，最后汇总结果
Multithreaded segmented sum, finally aggregate results
每个线程计算自己区间和，主线程用join等待所有线程结束，再累加各线程结果。
Each thread calculates its own range sum, main thread waits for all threads to finish with join, then sums all results.

6
三个窗口同时卖票
Three windows sell tickets simultaneously
模拟三个窗口（线程）同时卖票，保证线程安全
Simulate three windows (threads) selling tickets at the same time, ensure thread safety
用synchronized锁住票数对象，每个窗口卖票前获取锁，卖完后释放锁，防止超卖。
Use synchronized to lock the ticket object, each window acquires the lock before selling, releases after selling, prevents overselling.

7
生产者消费者
Producer and consumer
多个生产者线程和消费者线程共享缓冲区
Multiple producer and consumer threads share a buffer
生产者生产后通知消费者，消费者消费后通知生产者，用锁或队列控制缓冲区满/空时的等待。
Producer notifies consumer after producing, consumer notifies producer after consuming, use lock or queue to control waiting when buffer is full/empty.

7.1
生产者消费者（synchronized方式）
Producer and consumer (synchronized way)
用synchronized实现生产消费模型
Use synchronized to implement producer-consumer model
用synchronized+wait/notify，缓冲区满时生产者wait，空时消费者wait，操作后notifyAll。
Use synchronized + wait/notify, producer waits when buffer is full, consumer waits when empty, notifyAll after operation.

7.2
生产者消费者（ReentrantLock方式）
Producer and consumer (ReentrantLock way)
用ReentrantLock实现生产消费模型
Use ReentrantLock to implement producer-consumer model
用ReentrantLock+Condition，缓冲区满时notFull.await()，空时notEmpty.await()，操作后signal。
Use ReentrantLock + Condition, notFull.await() when buffer is full, notEmpty.await() when empty, signal after operation.

7.3
生产者消费者（BlockingQueue方式）
Producer and consumer (BlockingQueue way)
用BlockingQueue实现生产消费模型
Use BlockingQueue to implement producer-consumer model
生产者调用put()，队列满时自动阻塞；消费者调用take()，队列空时自动阻塞。
Producer calls put(), blocks when queue is full; consumer calls take(), blocks when queue is empty.

8
两线程交替打印a,b，共打印100次
Two threads alternately print a and b, total 100 times
两个线程交替打印字符a和b，共100次
Two threads alternately print characters a and b, total 100 times
共享标志位，一个线程打印a后修改标志并唤醒另一线程，自己等待；另一线程同理，循环50次。
Shared flag, one thread prints a, modifies flag and wakes up the other, then waits; the other does the same, loop 50 times.

9
多线程抢红包
Multithreaded red envelope grabbing
模拟多个线程同时抢红包，红包金额随机分配，保证线程安全和红包不被重复抢
Simulate multiple threads grabbing red envelopes at the same time, random allocation of amount, ensure thread safety and no duplicate grabbing
用锁或原子变量控制红包剩余数量，抢红包时先判断再扣减，扣减成功则分配金额。
Use lock or atomic variable to control remaining envelopes, check before grabbing, deduct if successful, then allocate amount.

10
手写线程池
Handwritten thread pool
不使用Java自带线程池，手动实现一个简单线程池
Implement a simple thread pool manually, not using Java's built-in pool
维护任务队列和工作线程集合，线程不断从队列取任务执行，队列空时线程等待，新任务时唤醒。
Maintain task queue and worker thread set, threads keep taking tasks from queue, wait when empty, wake up on new task.

11
如何实现一个安全的单例模式（双重检查锁）
How to implement a thread-safe singleton (double-checked locking)
设计线程安全的单例，考虑延迟加载与性能
Design thread-safe singleton, consider lazy loading and performance
私有构造器，静态volatile实例，第一次检查不加锁，第二次加锁创建实例，防止指令重排。
Private constructor, static volatile instance, first check without lock, second check with lock to create instance, prevent instruction reordering.

12
三个线程交替打印1-100，线程1打印1，线程2打印2，线程3打印3，线程1打印4...
Three threads alternately print 1-100, thread 1 prints 1, thread 2 prints 2, thread 3 prints 3, thread 1 prints 4...
三个线程按顺序循环打印1-100
Three threads print 1-100 in order
共享计数器，每个线程判断计数器%3==自己编号时打印并自增，否则等待，打印后唤醒所有线程。
Shared counter, each thread prints and increments when counter%3==its number, otherwise waits, wakes up all after printing.

13
线程池满了，新的任务来了怎么办？
What if thread pool is full and new tasks arrive?
线程池任务队列满时的处理策略
Thread pool queue full handling strategy
根据拒绝策略处理：AbortPolicy抛异常，CallerRunsPolicy由提交线程执行，DiscardPolicy丢弃。
Handle by rejection policy: AbortPolicy throws exception, CallerRunsPolicy runs by submitter, DiscardPolicy discards.

14
如何避免死锁？写一个死锁的例子并解决
How to avoid deadlock? Write and solve a deadlock example
模拟死锁场景，给出解决方案
Simulate deadlock scenario, give solution
死锁例子：两个线程各持一把锁，再请求对方锁；解决：按固定顺序获取锁或使用tryLock超时。
Deadlock example: two threads each hold a lock and request the other's; solution: acquire locks in fixed order or use tryLock with timeout.

15
多个线程按照指定顺序执行（如T1->T2->T3）
Multiple threads execute in specified order (e.g. T1->T2->T3)
多个线程按指定先后顺序执行
Multiple threads execute in specified sequence
用CountDownLatch，前一个线程结束后countDown，后一个线程await；或用Join逐个等待。
Use CountDownLatch, previous thread countDown after finish, next thread await; or use Join to wait one by one.

16
手写一个分布式锁（基于Redis）
Handwritten distributed lock (based on Redis)
用Redis实现分布式锁
Implement distributed lock with Redis
SETNX加锁，设置过期时间防死锁，解锁用Lua脚本保证原子性，释放锁时校验锁值。
SETNX to lock, set expiration to prevent deadlock, use Lua script for atomic unlock, check lock value when releasing.

17
手写一个分布式锁（基于Zookeeper）
Handwritten distributed lock (based on Zookeeper)
用Zookeeper实现分布式锁
Implement distributed lock with Zookeeper
创建临时节点，成功获取锁，监听前一个节点，前一个节点删除时唤醒，自己获取锁。
Create ephemeral node, acquire lock, watch previous node, wake up when previous node deleted, acquire lock yourself.

18
手写一个简单的限流算法（令牌桶）
Handwritten simple rate limiter (token bucket)
实现令牌桶限流算法
Implement token bucket rate limiting
定时生成令牌放入桶，请求时取令牌，桶空则拒绝，用线程安全队列或原子变量控制桶状态。
Generate tokens periodically into bucket, take token on request, reject if empty, use thread-safe queue or atomic variable to control bucket.

19
手写一个简单的限流算法（滑动窗口）
Handwritten simple rate limiter (sliding window)
实现滑动窗口限流算法
Implement sliding window rate limiting
记录每个时间片请求数，滑动时移除过期请求，统计当前窗口请求数，超阈值则限流。
Record request count per time slice, remove expired requests when sliding, count current window requests, limit if over threshold.

20
手写一个简单的MQ消息队列
Handwritten simple MQ message queue
实现简单的消息队列
Implement simple message queue
用阻塞队列存储消息，生产者put，消费者take，支持异步发送和接收，可扩展为持久化。
Use blocking queue to store messages, producer put, consumer take, support async send/receive, can extend to persistence.

21
手写一个简单的RPC框架
Handwritten simple RPC framework
实现远程过程调用框架
Implement remote procedure call framework
定义服务接口，客户端代理通过网络发送请求，服务端反射调用并返回结果，用Netty或Socket通信。
Define service interface, client proxy sends request over network, server calls by reflection and returns result, use Netty or Socket for communication.

22
手写一个简单的Spring容器
Handwritten simple Spring container
实现依赖注入和Bean管理
Implement dependency injection and bean management
解析XML/注解配置，实例化Bean，依赖注入，管理Bean生命周期，实现简单IoC容器。
Parse XML/annotation config, instantiate beans, inject dependencies, manage bean lifecycle, implement simple IoC container.

23
手写一个LRU缓存
Handwritten LRU cache
实现最近最少使用缓存算法
Implement least recently used cache algorithm
用HashMap+双向链表，访问时移到链表头，满时删除链表尾，O(1)时间复杂度。
Use HashMap + doubly linked list, move to head on access, remove tail when full, O(1) time complexity.

24
手写一个布隆过滤器
Handwritten Bloom filter
实现布隆过滤器用于去重
Implement Bloom filter for deduplication
多个哈希函数映射到位数组，添加时计算多个位置置1，查询时所有位为1才存在，可能有误判。
Multiple hash functions map to bit array, set multiple positions to 1 when adding, all bits 1 means exists, may have false positives.

25
手写一个简单的动态线程池
Handwritten simple dynamic thread pool
实现可动态调整参数的线程池
Implement thread pool with dynamic parameter adjustment
线程池参数（核心数、最大数、队列大小）可配置，通过配置中心动态更新，运行时生效。
Thread pool parameters (core size, max size, queue size) configurable, update via config center, take effect at runtime.

26
手写一个简单的定时任务调度器
Handwritten simple scheduled task scheduler
实现定时任务调度功能
Implement scheduled task functionality
维护任务队列，用线程定时扫描，到期执行任务，支持固定频率和固定延迟调度。
Maintain task queue, scan with thread regularly, execute on due, support fixed rate and fixed delay scheduling.

27
手写一个简单的数据库连接池
Handwritten simple database connection pool
实现数据库连接池
Implement database connection pool
初始化时创建一批连接，用栈或队列管理，请求时获取连接，用完归还，空闲连接超时释放。
Create connections at init, manage with stack or queue, get connection on request, return after use, release idle connections after timeout.

28
手写一个简单的缓存（支持过期时间）
Handwritten simple cache (with expiration)
实现带过期时间的缓存
Implement cache with expiration
用ConcurrentHashMap存储键值对，记录过期时间，访问时检查是否过期，过期则清除。
Use ConcurrentHashMap to store key-value pairs, record expiration, check on access, clear if expired.

29
手写一个简单的幂等性接口
Handwritten simple idempotent interface
实现幂等性接口
Implement idempotent interface
用唯一ID+Redis或数据库记录已处理请求，处理前先检查，已处理则直接返回成功。
Use unique ID + Redis or DB to record processed requests, check before processing, return success if already processed.

30
手写一个简单的分库分表路由
Handwritten simple database/table sharding router
实现分库分表路由逻辑
Implement sharding router logic
根据分片键（如用户ID）哈希计算库和表索引，路由到对应库表，支持查询和写入路由。
Hash by sharding key (e.g. user ID) to calculate DB/table index, route to corresponding DB/table, support query and write routing.