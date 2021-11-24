package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Window extends JFrame {
    Animator animator;
    Graphics g;
    int n = 15;

    public static void main(String[] args) { //по умолчанию создаются невидимые окна
        new Window();
    }


    public Window() {
        super();
        this.setVisible(true); //через this к этому классу обращаемся
        this.setDefaultCloseOperation(Window.EXIT_ON_CLOSE);//завершить программу
        this.setBounds(200, 50, 800, 600);
        this.setResizable(true);
        this.setLayout(null);// вывод компонентов, по умолчанию - заполнение всего окна, null-вывод строго по координатам
        this.setTitle("График");


        g = this.getGraphics();

       /* JPanel p = new JPanel();
        //p.setSize(this.getSize());
        //  p.setBounds(this.getBounds());
        p.setBounds(130, 0, this.getWidth(), this.getHeight());
        p.setVisible(true);
        p.setBackground(Color.GREY);
        this.add(p);*/

        Button b1 = new Button();
        b1.setLabel("Отобразить");
        b1.setBounds(10, 10, 100, 20);
        b1.setBackground(Color.PINK);
        this.add(b1);

        animator = new Animator(g, getWidth(), getHeight());
        animator.start();//60 раз в секунду перерисовываем
        System.out.println("аниматор создан");


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX());
                System.out.println(e.getY());
                double xCart = (e.getX() - getWidth() * 1.0 / 2) / n;
                double yCart = (getHeight() * 1.0 / 2 - e.getY()) / n;
                g.drawString(" (" + xCart + "," + yCart + ")", e.getX(), e.getY());
                g.fillOval(e.getX(), e.getY(), 3, 3);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                g.setColor(Color.WHITE);
                g.fillRect(0,0,getWidth(),getHeight());
                g.setColor(Color.BLACK);
                g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
                g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

                int count = 0;

                for (int i = getWidth() / 2; i < getWidth(); i++) {
                    // g.fillOval(i, getHeight() / 2, 1, 1);
                    g.drawString(count + "", i, getHeight() / 2);
                    count++;
                    i += n;
                }

                count = 1;
                for (int i = getWidth() / 2 - n; i > 0; i--) {
                    //  g.fillOval(i, getHeight() / 2, 1, 1);
                    g.drawString("-" + count + "", i, getHeight() / 2);
                    count++;
                    i -= n;
                }

                count = 1;
                for (int i = getHeight() / 2 - n; i > 0; i--) {
                    //   g.fillOval(getWidth() / 2, i, 1, 1);
                    g.drawString(count + "", getWidth() / 2, i);
                    count++;
                    i -= n;
                }

                count = 1;
                for (int i = getHeight() / 2 + n; i < getHeight(); i++) {
                    //  g.fillOval(getWidth() / 2, i, 1, 1);
                    g.drawString("-" + count + "", getWidth() / 2, i);
                    count++;
                    i += n;
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double scale = e.getPreciseWheelRotation();
                n+=scale;
            }
        });

    }
}
//сделать изменение масштаба по колесику
// чтобы n менялось при прокручивании колесика мышки
// если меняем только n то центр остается на месте,если хотим динамическое изменение, нужно менять менять центр
// относительные координаты должны остаться неизменными
//перерисовывать полностью график, заменяя старый центр на новый


//вопросы: как постоянно, а не один раз передавать аниматору ширину и высоту формы, чтобы при перерисовывании центрировалось?
// чтобы одновременно с прокручиванием изменялся масштаб нужно в refresh занести?
// как в refresh передать
// как-то через функцию задать getwidth getheight?