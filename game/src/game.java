
import java.awt.Color;
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
	boolean keyUp = false; // 위쪽 화살표가 눌러지지않은채로있다.
	boolean keyDown = false;// 아래쪽 화살표가 눌러지지않은채로있다.
	boolean keyLeft = false;// 왼쪽 화살표가 눌러지지않은채로있다.
	boolean keyRight = false;// 오른쪽 화살표가 눌러지지않은채로있다.
	boolean space = false;
	boolean shift = false;
	boolean p = false;
	boolean et = false;
	boolean r = false;
	
	Image stdimg = new ImageIcon("학생.png").getImage();
	Image background = new ImageIcon("강의실.png").getImage();
	Image level1 = new ImageIcon("초급.jpg").getImage();
	Image level2 = new ImageIcon("중급.png").getImage();
	Image level3 = new ImageIcon("고급.jpg").getImage();
	Image buffimg = null; // 더블버퍼링을 사용하기위한 버퍼이미지를 정의한다
	Graphics gc; // 오브젝트들을 그리기 위한 그래픽 툴을 정의한다
	
	int x = 400, y = 300; // 캐릭터의 시작 위치, 그리고 앞으로의 좌표를 받아오기 위한 변수
	int cnt = 0; // 쓰레드의 루프를 세는 변수, 각종 변수를 통제하기 위해 사용된다
	int mode = 0;
	int selectmode = 1;
	int life = 0; // 목숨
	boolean pause = false;
	boolean ghost = false;
	
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
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keyLeft = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keyRight = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			keyUp = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keyDown = true;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (mode != 3) {
				space = true;
			} //고급모드에서는 부스터가 사용되지않으므로
		} else if (e.getKeyCode() == KeyEvent.VK_P) {
			p = true;
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			r = true;
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			et = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keyLeft = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keyRight = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			keyUp = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keyDown = false;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			space = false;
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shift = false;
		} else if (e.getKeyCode() == KeyEvent.VK_P) {
			p = false;
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			r = false;
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			et = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				arrowkey(); // 받은 키에 따른 캐릭터의 이동을 통제
				repaint(); // 리페인트함수(그림을 그때그때 새로기리기위함)
				modeandpause();
				Thread.sleep(20);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 20밀리세컨드당 한번의 루프가 돌아간다
		}
	}
	
	public void drawimages(Graphics g) {
		if (mode == 0) {
			setBackground(Color.BLACK);
			if (selectmode == 1) {
				gc.drawImage(level1, 0,10, this);
			} else if (selectmode == 2) {
				gc.drawImage(level2, 0, 10, this);
			} else if (selectmode == 3) {
				gc.drawImage(level3, 0, 10, this);
			}
			g.drawImage(buffimg, 0, 0, this);
		} // 게임 난이도에 따라서 이미지를 나타냅니다.
		if (mode != 0) {
			backgroundDrawImg(); // 배경의 그림을 그린다
			g.drawImage(buffimg, 0, 0, this); // 버퍼이미지를 그린다. 0,0으로 좌표를 맞춰서프레임크기에
												// 딱맞춘다
		}
	}
	
	public void backgroundDrawImg() {
		gc.drawImage(background, 0, 0, this); // 가져온 배경이미지파일을 0,0에 위치시킨다
	}
	
	public void arrowkey() { // 캐릭터의 이동속도와 방향키에 따른 이동방향을 결정하고, 캐릭터를 화면
		// 밖으로못빠져나가가게합니다. 그리고 부스터 또한 통제합니다
		if (mode == 0) {
			if (keyLeft == true) {
				selectmode--;
				if (selectmode == 0) {
					selectmode = 3;
				}
				keyLeft = false;
			}
			if (keyRight == true) {
				selectmode++;
				if (selectmode == 4) {
					selectmode = 1;
				}
				keyRight = false;
			}
		} else {
			if (keyUp == true && pause == false) {
				if (y > 0) {
					if (space == false) {
						y -= 8;
					} else {
						y -= 15;
					}
				}
			}
			if (keyDown == true && pause == false) {
				if (y < 570) {
					if (space == false) {
						y += 8;
					} else {
						y += 15;
					}
				}
			}
			if (keyLeft == true && pause == false) {
				if (x > 0) {
					if (space == false) {
						x -= 8;
					} else {
						x -= 15;
					}
				}
			}
			if (keyRight == true && pause == false) {
				if (x < 780) {
					if (space == false) {
						x += 8;
					} else {
						x += 15;
					}
				}
			}
			if (et == true) {
				mode = selectmode;
			}
		}
	}
	
	public void modeandpause() {
		if (mode == 0) {
			if (et == true) {
				mode = selectmode; //엔터를 누르면 해당모드를 적용시킨 후 게임이 시작됩니다
			}
			if (mode == 1) { // 초급에서 사용되는 아이템들입니다.
				
			} else if (mode == 2) {
				
			} else if (mode == 3) {
				
			}
		}
		if (p == true) { // p를 누르면 게임이 멈춥니다
			pause = !pause;
			p = false;
		}
	}
	
}

