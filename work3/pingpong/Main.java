package work3.pingpong;

public class Main {
    public static void main(String[] args) {
        Object monitor = new Object();
        PingPong ping = new PingPong(monitor, "PING", true);
        PingPong pong = new PingPong(monitor, "PONG", false);
        Thread pingThread = new Thread(ping);
        Thread pongThread = new Thread(pong);
        pingThread.start();
        pongThread.start();
    }

    private static class PingPong implements Runnable{
        private static boolean ping = true;
        private final Object monitor;
        private final String msg;
        private final boolean isPing;

        public PingPong(Object monitor, String msg, boolean isPing) {
            this.monitor = monitor;
            this.msg = msg;
            this.isPing = isPing;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
//            while (true) {
                synchronized (monitor) {
                    if (ping == isPing) {
                        monitor.notify();
                        System.out.println(i + " " + msg);
                        ping = !ping;
                        try {
                            monitor.wait(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
