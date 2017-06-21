package jpcappacketcapture;


import javax.swing.SwingUtilities ; 


public abstract class CaptureThread {   
    
    private Object value ; 

/** 
* Class to maintain reference to current thread
* under separate synchronization control.
*/ 

    private static class ThreadVar {
        private Thread thread ;
        ThreadVar (Thread t ){
            thread = t;
        }
        synchronized Thread get(){
            return thread ; 
        }
        synchronized void clear(){
            thread = null ; 
        }
    }
    
    private ThreadVar threadVar ; 
     
/** 
 * Get the value produced by the worker thread, or null if it 
 * hasn't been constructed yet.
     * @return 
 */
    protected synchronized Object getValue(){
        return value ; 
    }
/** 
 * Set the value produced by worker thread 
 */
    private synchronized void setValue(Object x){
        value = x ; 
    }
   
    /** 
     * Compute the value to be returned by the <code>get</code> method. 
     * @return 
     */
    public abstract Object construct();
    
    /**
     * Called on the event dispatching thread (not on the worker thread)
     * after the <code>construct</code> method has returned.
     */
    public void finished(){};
    
    /**
     * A new method that interrupts the worker thread.  Call this method
     * to force the worker to stop what it's doing.
     */
    public void interrupt(){
        Thread t = threadVar.get(); 
        
        if (t != null){
            t.interrupt();
        }
        threadVar.clear();
    }
    
    /**
     * Return the value created by the <code>construct</code> method.  
     * Returns null if either the constructing thread or the current
     * thread was interrupted before a value was produced.
     * 
     * @return the value created by the <code>construct</code> method
     */
    public Object get(){
        while (true){
            Thread t = threadVar.get(); 
            if (t==null){
                return getValue(); 
            }
            
         try 
         {
           t.join();
         }
         catch (InterruptedException e){
             Thread.currentThread().interrupt();
             return null ;
            }
        }
    }
    
    /**
     * Start a thread that will call the <code>construct</code> method
     * and then exit.
     */
    public CaptureThread(){
        final Runnable doFinished = new Runnable(){
            @Override
            public void run() {
                finished () ; 
            }
        };
        Runnable doConstruct = new Runnable(){
            @Override
            public void run(){
                try {
                    setValue(construct());
                }
                finally {
                    threadVar.clear();
                }
                SwingUtilities.invokeLater(doFinished);
            }
        };
        
        Thread t = new Thread(doConstruct); 
        threadVar = new ThreadVar(t); 
        
    } 
    
    /**
     * Start the thread.
     */
    public void start(){
        Thread t = threadVar.get();
        if (t != null)
        {
            t.start();
        }
    }
}