package converter;


import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainCard extends JFrame
{
  private JTextArea textArea;
  private static final long serialVersionUID = 6700608566962888967L;
  private JTextField textField_4;
  private JTextField textField_3;
  private JTextField textField_1;
  private JTextField textField;
  JProgressBar progressBar1;
  JProgressBar progressBar2;
  File WorkPath;
  File file;
  File tmp;
  JLabel output1;
  JLabel output2;
  Object lock = new Object();
  Thread myThread;
  JButton StartButton1;
  JButton StartButton2;

  public static void main(String[] args)
  {
    try
    {
      MainCard frame = new MainCard();
      frame.setVisible(true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public MainCard()
  {
    getContentPane().setLayout(null);
    setTitle("通讯录转置器");
    setBounds(100, 100, 473, 277);
    setDefaultCloseOperation(3);

    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.setBounds(0, 0, 465, 243);
    getContentPane().add(tabbedPane);

    JPanel panelStoM = new JPanel();
    panelStoM.setLayout(null);
    tabbedPane.addTab("单个到多个", null, panelStoM, null);

    JLabel label_3 = new JLabel();
    label_3.setText("需拆分名片：");
    label_3.setBounds(23, 10, 78, 27);
    panelStoM.add(label_3);

    this.textField_3 = new JTextField();
    this.textField_3.setBounds(103, 13, 238, 21);
    panelStoM.add(this.textField_3);

    JButton button_4 = new JButton();
    button_4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        MainCard.this.ChooseFile();
      }
    });
    button_4.setText("选择");
    button_4.setBounds(359, 12, 72, 23);
    panelStoM.add(button_4);

    JButton button_1_1 = new JButton();
    button_1_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        MainCard.this.SingleToMultipleDestination();
      }
    });
    button_1_1.setText("选择");
    button_1_1.setBounds(359, 64, 72, 23);
    panelStoM.add(button_1_1);

    this.textField_4 = new JTextField();
    this.textField_4.setBounds(103, 65, 238, 21);
    panelStoM.add(this.textField_4);

    JLabel label_1_1 = new JLabel();
    label_1_1.setText("输出目录：");
    label_1_1.setBounds(23, 62, 78, 27);
    panelStoM.add(label_1_1);

    JLabel label_2_1 = new JLabel();
    label_2_1.setText("转换进度：");
    label_2_1.setBounds(103, 157, 72, 27);
    panelStoM.add(label_2_1);

    this.progressBar1 = new JProgressBar();
    this.progressBar1.setStringPainted(true);
    this.progressBar1.setBounds(174, 163, 167, 19);
    panelStoM.add(this.progressBar1);

    this.StartButton1 = new JButton();
    this.StartButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        MainCard.this.start1();
      }
    });
    this.StartButton1.setText("开始拆分");
    this.StartButton1.setBounds(103, 113, 88, 23);
    panelStoM.add(this.StartButton1);

    JButton button_3_1 = new JButton();
    button_3_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        MainCard.this.textField_3.setText("");
        MainCard.this.textField_4.setText("");
        MainCard.this.progressBar1.setValue(0);
        MainCard.this.output1.setText("0");
      }
    });
    button_3_1.setText("清空");
    button_3_1.setBounds(359, 113, 72, 23);
    panelStoM.add(button_3_1);

    this.output1 = new JLabel();
    this.output1.setText("0");
    this.output1.setBounds(359, 163, 72, 15);
    panelStoM.add(this.output1);

    JPanel panelMtoS = new JPanel();
    panelMtoS.setLayout(null);
    tabbedPane.addTab("多个到单个", null, panelMtoS, null);

    JLabel label = new JLabel();
    label.setText("名片文件夹：");
    label.setBounds(22, 10, 79, 27);
    panelMtoS.add(label);

    this.textField = new JTextField();
    this.textField.setBounds(100, 13, 238, 21);
    panelMtoS.add(this.textField);

    JButton button = new JButton();
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        MainCard.this.ChoosePath();
      }
    });
    button.setText("选择");
    button.setBounds(356, 12, 72, 23);
    panelMtoS.add(button);

    JLabel label_1 = new JLabel();
    label_1.setText("输出目录：");
    label_1.setBounds(22, 62, 79, 27);
    panelMtoS.add(label_1);

    JButton button_1 = new JButton();
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        MainCard.this.ChooseDestination();
      }
    });
    button_1.setText("选择");
    button_1.setBounds(356, 64, 72, 23);
    panelMtoS.add(button_1);

    this.textField_1 = new JTextField();
    this.textField_1.setBounds(100, 65, 238, 21);
    panelMtoS.add(this.textField_1);

    this.progressBar2 = new JProgressBar();
    this.progressBar2.setValue(0);
    this.progressBar2.setStringPainted(true);
    this.progressBar2.setBounds(166, 169, 172, 19);
    panelMtoS.add(this.progressBar2);

    this.StartButton2 = new JButton();
    this.StartButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        MainCard.this.start2();
      }
    });
    this.StartButton2.setText("开始合并");
    this.StartButton2.setBounds(100, 117, 88, 23);
    panelMtoS.add(this.StartButton2);

    JButton button_3 = new JButton();
    button_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        MainCard.this.textField.setText("");
        MainCard.this.textField_1.setText("");
        MainCard.this.progressBar2.setValue(0);
        MainCard.this.output2.setText("0");
      }
    });
    button_3.setText("清空");
    button_3.setBounds(356, 117, 72, 23);
    panelMtoS.add(button_3);

    JLabel label_2 = new JLabel();
    label_2.setText("转换进度：");
    label_2.setBounds(100, 163, 72, 27);
    panelMtoS.add(label_2);

    this.output2 = new JLabel();
    this.output2.setText("0");
    this.output2.setBounds(368, 169, 82, 15);
    panelMtoS.add(this.output2);

    JPanel panel = new JPanel();
    panel.setLayout(null);
    tabbedPane.addTab("帮助", null, panel, null);

    this.textArea = new JTextArea();
    this.textArea.setText("             通讯录转置器                \n不同平台手机名片文件保存方式不一样，有些手机将所有的名片保存在一个vcf文\n件中，有些手机每个名片保存为一个vcf文件。\r\n本工具旨在实现将vcf文件合并或者拆分，即实现两种手机名片保存方式之间的转\n换。仅经本人测试用于nokia N73和motolora milestone之间的转换，如有纰漏\n请多多包含。\r\nEmail：sp_wade@hotmail.com");

    this.textArea.setBounds(0, 0, 460, 214);
    panel.add(this.textArea);
  }

  protected void start2()
  {
    try
    {
      this.output2.setText("读取文件。。。");

      File[] vCardsFile = this.tmp.listFiles();
      int num = vCardsFile.length;
      String[] vCards = new String[num];
      for (int i = 0; i < num; i++) {
        vCards[i] = vCardsFile[i].toString();
      }
      if (this.myThread == null) {
        this.myThread = new TaskThread(num, vCards, this.WorkPath, this.output2, this.StartButton2, this.progressBar2);
        this.myThread.start();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void start1()
  {
    try {
      this.output1.setText("读取文件。。。");
      String[] vCards = new String[1000];
      vCards = readinfile(vCards, this.textField_3.getText());
      int num = 0;
      for (int i = 0; i < vCards.length; i++) {
        if (vCards[i] != null) {
          num++;
        }
      }
      if (this.myThread == null) {
        this.myThread = new TaskThread(num, vCards, this.WorkPath, this.output1, this.StartButton1, this.progressBar1);
        this.myThread.start();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  protected void SingleToMultipleDestination()
  {
    this.WorkPath = PathChooser(this.WorkPath);
    this.textField_4.setText(String.valueOf(this.WorkPath));
  }

  protected void ChooseFile()
  {
    File[] f = { this.file, this.WorkPath };
    f = Chooser(this.file, this.WorkPath);
    this.WorkPath = f[1];
    this.textField_3.setText(String.valueOf(f[0]));
  }

  protected void ChooseDestination()
  {
    this.WorkPath = PathChooser(this.WorkPath);
    this.textField_1.setText(String.valueOf(this.WorkPath));
  }

  protected void ChoosePath()
  {
    this.WorkPath = PathChooser(this.WorkPath);
    this.tmp = this.WorkPath;
    this.textField.setText(String.valueOf(this.WorkPath));
  }

  public File PathChooser(File path) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(1);

    fileChooser.setFont(new Font("瀹嬩綋", 0, 12));
    fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
    if (path != null) {
      fileChooser.setCurrentDirectory(path);
    }
    int result = fileChooser.showOpenDialog(null);
    if (result == 0) {
      path = fileChooser.getSelectedFile();
      return path;
    }if (result != 1);
    return path;
  }
  public File[] Chooser(File file, File path) {
    File[] f = new File[2];
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(0);

    fileChooser.setFont(new Font("瀹嬩綋", 0, 12));
    fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
    if (path != null) {
      fileChooser.setCurrentDirectory(path);
    }
    int result = fileChooser.showOpenDialog(null);
    if (result == 0) {
      file = fileChooser.getSelectedFile();
      path = fileChooser.getCurrentDirectory();
      f[0] = file;
      f[1] = path;
      return f;
    }if (result != 1);
    f[0] = file;
    f[1] = path;
    return f;
  }

  public String[] readinfile(String[] items, String filename) throws Exception
  {
    try {
      int i = 0;
      FileReader fr = new FileReader(filename);
      BufferedReader in = new BufferedReader(fr);
      String line = in.readLine();
      while (line != null) {
        if (line.equals("BEGIN:VCARD")) {
          items[i] = (line + "\r\n");
        } else if (line.equals("END:VCARD")) {
          items[i] = (items[i] + line + "\r\n");
          i++;
        } else {
          items[i] = (items[i] + line + "\r\n");
        }

        line = in.readLine();
      }
      in.close();
      fr.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return items;
  }

  public void WriteToFile(String[] items) {
    try {
      String filename = null;
      FileWriter fw = new FileWriter(filename);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("");
    }
    catch (IOException e) {
      e.printStackTrace(); }  } 
  class TaskThread extends Thread { private int max;
    private JLabel output;
    private JButton StartButton;
    private JProgressBar progressBar;
    private String[] vCards;
    private File Destination;

    public TaskThread(int num, String[] vCards, File path, JLabel output, JButton StartButton, JProgressBar progressBar) { this.max = num;
      this.output = output;
      this.StartButton = StartButton;
      this.progressBar = progressBar;
      this.vCards = vCards;
      this.Destination = path; }

    public void run() {
      int min = 0;
      this.progressBar.setValue(min);
      this.progressBar.setMinimum(min);
      this.progressBar.setMaximum(this.max);
      Runnable runner = new Runnable() {
        public void run() {
          int value = MainCard.TaskThread.this.progressBar.getValue();
          if (MainCard.TaskThread.this.output.equals(MainCard.this.output1))
            try {
              String filename = MainCard.TaskThread.this.Destination + File.separator + (value + 1) + ".vcf";
              BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
              bw.write(MainCard.TaskThread.this.vCards[value]);
              bw.close();
            }
            catch (IOException e) {
              e.printStackTrace();
            }
          else if (MainCard.TaskThread.this.output.equals(MainCard.this.output2)) {
            try {
              String mergecontent = "";
              String filename = MainCard.TaskThread.this.Destination + File.separator + "merged.vcf";
              BufferedReader br = new BufferedReader(new FileReader(MainCard.TaskThread.this.vCards[value]));
              String line = br.readLine();
              while (line != null) {
                mergecontent = mergecontent + line + "\r\n";
                line = br.readLine();
              }
              File wf = new File(filename);
              FileWriter fw = new FileWriter(wf, true);
              fw.append(mergecontent);
              fw.close();
              br.close();
            }
            catch (IOException e) {
              e.printStackTrace();
            }
          }

          value++;

          MainCard.TaskThread.this.progressBar.setValue(value);
          MainCard.TaskThread.this.output.setText("Number:" + value);
        }
      };
      for (int i = min; i < this.max; i++) {
        try {
          SwingUtilities.invokeAndWait(runner);
        } catch (InvocationTargetException e) {
          break;
        }
        catch (InterruptedException localInterruptedException) {
        }
        synchronized (MainCard.this.lock) {
          try {
            MainCard.this.lock.wait(2L);
          }
          catch (InterruptedException localInterruptedException1) {
          }
        }
      }
      this.StartButton.setEnabled(true);
      MainCard.this.myThread = null;
    }
  }
}