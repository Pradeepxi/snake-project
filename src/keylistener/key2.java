package keylistener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class key2 extends JPanel implements KeyListener
{
	int[] x=new int[750];
	int [] y=new int[750];
	int mooves=0;
	boolean right=false,up=false,left=false,down=false,gameover=false;
	ImageIcon title,rmouth,lmouth,body,dmouth,umouth,ball;
	int ballx[]= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,
			850,875,900,925,950,975,1000,1025,1050,1075,1100,1125,1150,1175,1200};//48
	int bally[]= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700};//26
	Random rand=new Random();
	int randx=rand.nextInt(48);
	int randy=rand.nextInt(26);
	int snakelength=3;
	int score=0;
	public static void main(String [] arg)
	{
		new key2();
	}
	public key2()
	{
		JFrame j=new JFrame();
		j.add(this);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(1500,850);
		j.addKeyListener(this);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		mooves++;
		if(!gameover)
		{
			if(e.getKeyCode()==KeyEvent.VK_RIGHT && !left)
			{
				for(int i=snakelength-1;i>=0;i--)
					y[i+1]=y[i];
				for(int i=snakelength;i>=0;i--)
				{
					if(i==0)
						x[0]=x[0]+25;
					else
						x[i]=x[i-1];
					if(x[0]>1200)
						x[i]=25;
				}
				up=false;
				down=false;
				right=true;
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT && !right)
			{
				for(int i=snakelength-1;i>=0;i--)
					y[i+1]=y[i];
				for(int i=snakelength;i>=0;i--)
				{
					if(i==0)
						x[0]=x[0]-25;
					else
						x[i]=x[i-1];
					if(x[0]<25)
						x[i]=1200;}
				up=false;
				down=false;
				left=true;
			}
			if(e.getKeyCode()==KeyEvent.VK_UP && !down)
			{
				for(int i=snakelength-1;i>=0;i--)
					x[i+1]=x[i];
				for(int i=snakelength;i>=0;i--)
				{
					if(i==0)
						y[i]=y[i]-25;
					else
						y[i]=y[i-1];
					if(y[i]<70)
						y[i]=750;
				}
				right=false;
				left=false;
				up=true;
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN && !up)
			{
				for(int i=snakelength-1;i>=0;i--)
					x[i+1]=x[i];
				for(int i=snakelength;i>=0;i--)
				{
					if(i==0)
						y[i]=y[i]+25;
					else
						y[i]=y[i-1];
					if(y[i]>750)
						y[i]=75;
				}
				right=false;
				left=false;
				down=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			gameover=false;
			score=0;
			mooves=0;
			snakelength=3;
		}
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	public void paint(Graphics g)
	{
		if(mooves==0)
		{
			x[0]=100;
			x[1]=75;
			x[2]=50;
			x[3]=25;
			
			y[0]=100;
			y[1]=100;
			y[2]=100;
			y[3]=100;
		}
		//super.paint(g);
		g.setColor(Color.black);
		g.drawRect(25, 10, 1200, 55);
		title =new ImageIcon("snaketitle.jpg");
		title.paintIcon(this, g,195, 11);
		g.setFont(new Font("arial",Font.BOLD,19));
		g.drawString("Made by Pradeep Singh Mandwal ", 230, 40);
		g.setColor(Color.ORANGE);
		g.fillRect(26, 11, 194, 54);
		g.fillRect(1045, 11,179,54);
		g.setFont(new Font("arial",Font.BOLD,25));
		g.drawString("Score : "+score, 900, 45);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(25, 70,1200,700 );
		rmouth=new ImageIcon("rightmouth.png");
		rmouth.paintIcon(this, g, x[0], y[0]);
		for(int i=0;i<=snakelength;i++)
		{
			if(i==0 && right)
			{
				rmouth=new ImageIcon("rghtmouth.png");
				rmouth.paintIcon(this, g, x[i], y[i]);
			}
			if(i==0 && left)
			{
				lmouth=new ImageIcon("leftmouth.png");
				lmouth.paintIcon(this, g, x[i], y[i]);
			}
			if(i==0 && up)
			{
				umouth=new ImageIcon("upmouth.png");
				umouth.paintIcon(this, g, x[i], y[i]);
			}
			if(i==0 && down )
			{
				dmouth=new ImageIcon("downmouth.png");
				dmouth.paintIcon(this, g, x[i], y[i]);
			}
			if(i!=0)
			{
				body=new ImageIcon("snakeimage.png");
				body.paintIcon(this, g, x[i], y[i]);
			}
		}
		ball=new ImageIcon("enemy.png");
		ball.paintIcon(this, g, ballx[randx], bally[randy]);
		if(ballx[randx]==x[0] &&  bally[randy]==y[0])
		{ 
			snakelength++;
			score++;
			randx=rand.nextInt(48);
			randy=rand.nextInt(26);
		}
		for(int i=1;i<=snakelength;i++)
		{
			if(x[0]==x[i] && y[0]==y[i])
			{
				right=false;
				left=false;
				up=false;
				down=false;
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString(" GAMEOVER ", 432, 400);
				g.setFont(new Font("arial",Font.BOLD,30));
				g.drawString(" SCORE = "+score, 490, 480);
				g.drawString(" Press Enter to Restart", 420, 550);
				gameover=true;
			}
		}
		g.dispose();
	}
}
