
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
	Image stdimg = new ImageIcon("�л�.png").getImage();
	Image background = new ImageIcon("���ǽ�.png").getImage();
	
	Graphics gc; // ������Ʈ���� �׸��� ���� �׷��� ���� �����Ѵ�
	
	int x = 400, y = 300; // ĳ������ ���� ��ġ, �׸��� �������� ��ǥ�� �޾ƿ��� ���� ����
	
	int mode = 0;
	int selectmode = 1;
	Image level1 = new ImageIcon("�ʱ�.jpg").getImage();
	Image level2 = new ImageIcon("�߱�.png").getImage();
	Image level3 = new ImageIcon("���.jpg").getImage();
	Image buffimg = null; // ������۸��� ����ϱ����� �����̹����� �����Ѵ�
	
	Shooting_Frame() {
		setTitle("A+ or F");
		setSize(800, 600);
		start(); // �������� ������ �����ϱ� ���� �޽��
		setLocation(250, 80);
		setResizable(false); // ����� ������ �� ���� ����
		setVisible(true); // �������� ���̰� ����
		this.addKeyListener(this); // Ű�����ʸ� �߰��Ͽ� ����Ű ������ �޾ƿ� �� �ְ� �Ѵ�.
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
	} // ������ ���� �����̹����� �̿��Ͽ� ������ ������ ������ ���۴ϴ�.

	public void paint(Graphics g) { // ���� �̹����� �׸������Ѹ޼��带 �����Ų��
		buffimg = createImage(800, 600); // �����̹����� �׸��� (������۸��� ����Ͽ� ȭ���� ��������
											// ���ش�)
		gc = buffimg.getGraphics(); // �����̹����� ���� �׷��� ��ä�� ���´�.
		drawimages(g);
	}
	
	public void start() {
		Thread th = new Thread(this); // ������ �� ����
		th.start(); // �������� ������ ���۽�Ų��
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
			backgroundDrawImg(); // ����� �׸��� �׸���
			g.drawImage(buffimg, 0, 0, this); // �����̹����� �׸���. 0,0���� ��ǥ�� ���缭������ũ�⿡
												// �������
	}
	
	public void backgroundDrawImg() {
		gc.drawImage(background, 0, 0, this); // ������ ����̹��������� 0,0�� ��ġ��Ų��
	}
	
	
	
}

