package com.company;

import java.awt.*;

public class Animator extends Thread {

    private Graphics g;
    private int getW;
    private int getH;
    int n =15;


    public Animator(Graphics g, int getW, int getH) {
        this.g = g;
        this.getW = getW;
        this.getH = getH;

    }

    public void refresh() {
        g.drawLine(getW / 2, 0, getW / 2, getH);
        g.drawLine(0, getH / 2, getW, getH / 2);
        int count = 0;

        for (int i = getW / 2; i < getW; i++) {
            // g.fillOval(i, getHeight() / 2, 1, 1);
            g.drawString(count + "", i, getH / 2);
            count++;
            i += n;
        }

        count = 1;
        for (int i = getW / 2 - n; i > 0; i--) {
            //  g.fillOval(i, getHeight() / 2, 1, 1);
            g.drawString("-" + count + "", i, getH / 2);
            count++;
            i -= n;
        }

        count = 1;
        for (int i = getH / 2 - n; i > 0; i--) {
            //   g.fillOval(getWidth() / 2, i, 1, 1);
            g.drawString(count + "", getW / 2, i);
            count++;
            i -= n;
        }

        count = 1;
        for (int i = getH / 2 + n; i < getH; i++) {
            //  g.fillOval(getWidth() / 2, i, 1, 1);
            g.drawString("-" + count + "", getW/ 2, i);
            count++;
            i += n;
        }




    }

    @Override
    public void run() {
        super.run();
        while (true) {
            refresh();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
