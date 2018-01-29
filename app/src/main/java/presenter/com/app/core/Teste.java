package presenter.com.app.core;

import android.util.Log;

import presenter.com.app.Presenter;

public class Teste extends Thread {
    private Presenter p;

    public Teste(Presenter p) {
        this.p = p;
    }


    public void run() {
        long last = System.currentTimeMillis();
        while (true) {
            long now = System.currentTimeMillis();
            if (now - last > 2000) {
                p.iterate();
                System.out.println("Slide atual: " + p.getSlideAtual());
                last = now;
            }
        }
    }


    public void next(){
        Log.d("next","next"); // Debug

    }

    public void prev(){
        Log.d("prev","next");
    }

}
