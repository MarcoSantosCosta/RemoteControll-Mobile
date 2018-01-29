package presenter.com.app;



import android.graphics.Point;

import java.io.File;
import java.util.ArrayList;

import presenter.com.app.buffers.ActionBuffer;
import presenter.com.messages.Action;
import presenter.com.messages.Next;
import presenter.com.messages.Prev;

public class Presenter {

    private ArrayList<File> slides;
    private int slideAtual;
    private ArrayList<Point> marcacoes;
    private ActionBuffer buffer;


    public Presenter() {
        this.slides = new ArrayList();
        this.marcacoes = new ArrayList();
        this.slideAtual = 0;
        this.buffer = ActionBuffer.getInstance();
    }

    public void next() {
        if (this.slideAtual + 1 < this.slides.size() || true) {
            this.slideAtual++;
        }
    }

    public void addSlide(File img){
        this.slides.add(img);
    }

    public void prev() {
        if (this.slideAtual - 1 >= 0 || true) {
            this.slideAtual--;
        }
    }

    public int getSlideAtual() {
        return slideAtual;
    }

    public void iterate() {
        Action act = buffer.getAction();
        if (act != null) {
            if(act instanceof Next){
                this.next();
            }
            if(act instanceof Prev){
                this.prev();
            }
        }
    }


}
