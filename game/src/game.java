
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

public class game {

	public static void main(String[] args) {
		Shooting_Frame game = new Shooting_Frame();
	}

}

class Shooting_Frame extends Frame implements Runnable, KeyListener {
	Image stdimg = new ImageIcon("학생.png").getImage();
	Image background = new ImageIcon("강의실.png").getImage();
	
	Graphics gc; // 오브젝트들을 그리기 위한 그래픽 툴을 정의한다
	
	int x = 400, y = 300; // 캐릭터의 시작 위치, 그리고 앞으로의 좌표를 받아오기 위한 변수
	
	int mode = 0;
	int selectmode = 1;
	Image level1 = new ImageIcon("초급.jpg").getImage();
	Image level2 = new ImageIcon("중급.png").getImage();
	Image level3 = new ImageIcon("고급.jpg").getImage();
	Image buffimg = null; // 더블버퍼링을 사용하기위한 버퍼이미지를 정의한다
	
	Shooting_Frame() {
		setTitle("A+ or F");
		setSize(800, 600);
		start(); // 쓰레드의 루프를 시작하기 위한 메써드
		setLocation(250, 80);
		setResizable(false); // 사이즈를 조절할 수 없게 만듬
		setVisible(true); // 프레임을 보이게 만듬
		this.addKeyListener(this); // 키리스너를 추가하여 방향키 정보를 받아올 수 있게 한다.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
	}
	public void update(Graphics g) {
		paint(g);
	} // 프레임 내의 버퍼이미지를 이용하여 깜빡임 현상을 완전히 없앱니다.

	public void paint(Graphics g) { // 각종 이미지를 그리기위한메서드를 실행시킨다
		buffimg = createImage(800, 600); // 버퍼이미지를 그린다 (떠블버퍼링을 사용하여 화면의 깜빡임을
											// 없앤다)
		gc = buffimg.getGraphics(); // 버퍼이미지에 대한 그래픽 객채를 얻어온다.
		drawimages(g);
	}
	
	public void start() {
		Thread th = new Thread(this); // 쓰레드 를 정의
		th.start(); // 쓰레드의 루프를 시작시킨다
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void drawimages(Graphics g) {
			backgroundDrawImg(); // 배경의 그림을 그린다
			g.drawImage(buffimg, 0, 0, this); // 버퍼이미지를 그린다. 0,0으로 좌표를 맞춰서프레임크기에
												// 딱맞춘다
	}
	
	public void backgroundDrawImg() {
		gc.drawImage(background, 0, 0, this); // 가져온 배경이미지파일을 0,0에 위치시킨다
	}
	
	
	
}

