package ApexLegendsRPTracker;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField fieldCurrentRP;
	private JTextField fieldMatchPlace;
	private JTextField fieldKillCount;
	private JTextField fieldAssistCount;
	private JCheckBox chkAbandoned;
	private JLabel labelCurrentRP;
	private JLabel labelMatchPlace;
	private JLabel labelKillCount;
	private JLabel labelAssistCount;
	private JLabel labelMatchInfo;
	private JLabel labelGetPlacePoint;
	private JLabel labelGetKillPoint;
	private JLabel labelResult;
	private JButton btnCalc;
	@SuppressWarnings("unused")
	private final String cacheFilePath = new java.io.File(".").getAbsolutePath();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("ApexLegendsRPTracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fieldCurrentRP = new JTextField();
		fieldCurrentRP.setBounds(22, 55, 104, 20);
		contentPane.add(fieldCurrentRP);
		fieldCurrentRP.setColumns(10);

		fieldMatchPlace = new JTextField();
		fieldMatchPlace.setColumns(10);
		fieldMatchPlace.setBounds(160, 55, 104, 20);
		contentPane.add(fieldMatchPlace);

		fieldKillCount = new JTextField();
		fieldKillCount.setColumns(10);
		fieldKillCount.setBounds(299, 55, 104, 20);
		contentPane.add(fieldKillCount);

		fieldAssistCount = new JTextField();
		fieldAssistCount.setColumns(10);
		fieldAssistCount.setBounds(444, 55, 104, 20);
		contentPane.add(fieldAssistCount);

		chkAbandoned = new JCheckBox("マッチ放棄");
		chkAbandoned.setBounds(444, 129, 112, 24);
		contentPane.add(chkAbandoned);

		labelCurrentRP = new JLabel("保有RP");
		labelCurrentRP.setFont(new Font("HGS明朝B", Font.BOLD, 12));
		labelCurrentRP.setBounds(47, 25, 60, 18);
		contentPane.add(labelCurrentRP);

		labelMatchPlace = new JLabel("マッチ順位");
		labelMatchPlace.setFont(new Font("HGS明朝B", Font.BOLD, 12));
		labelMatchPlace.setBounds(178, 24, 79, 20);
		contentPane.add(labelMatchPlace);

		labelKillCount = new JLabel("キル数");
		labelKillCount.setFont(new Font("HGS明朝B", Font.BOLD, 12));
		labelKillCount.setBounds(329, 24, 60, 20);
		contentPane.add(labelKillCount);

		labelAssistCount = new JLabel("アシスト数");
		labelAssistCount.setFont(new Font("HGS明朝B", Font.BOLD, 12));
		labelAssistCount.setBounds(463, 24, 81, 20);
		contentPane.add(labelAssistCount);

		labelMatchInfo = new JLabel("");
		labelMatchInfo.setFont(new Font("HGS明朝B", Font.BOLD, 12));
		labelMatchInfo.setBounds(47, 133, 321, 16);
		contentPane.add(labelMatchInfo);

		labelGetPlacePoint = new JLabel("");
		labelGetPlacePoint.setFont(new Font("HGS明朝B", Font.BOLD, 12));
		labelGetPlacePoint.setBounds(47, 171, 321, 16);
		contentPane.add(labelGetPlacePoint);

		labelGetKillPoint = new JLabel("");
		labelGetKillPoint.setFont(new Font("HGS明朝B", Font.BOLD, 12));
		labelGetKillPoint.setBounds(47, 209, 321, 16);
		contentPane.add(labelGetKillPoint);

		labelResult = new JLabel("");
		labelResult.setFont(new Font("HGS明朝B", Font.BOLD, 12));
		labelResult.setBounds(47, 251, 321, 16);
		contentPane.add(labelResult);

		btnCalc = new JButton("計算");
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currentRP = Integer.parseInt(fieldCurrentRP.getText());
				int matchPlace = Integer.parseInt(fieldMatchPlace.getText());
				int killCount = Integer.parseInt(fieldKillCount.getText());
				int assistCount = Integer.parseInt(fieldAssistCount.getText());
				int count = killCount + assistCount;
				int result[] = Library.calcRPWithBreakdown(currentRP, matchPlace, killCount, assistCount, chkAbandoned.isSelected());
				int modifiedRP = currentRP + result[5];
				labelMatchInfo.setText("マッチ順位：" + matchPlace + "    キル/アシスト合計：" + count);
				labelGetPlacePoint.setText("順位点：" + result[0]);
				labelGetKillPoint.setText("キルポイント：" + result[1]);
				labelResult.setText("獲得RP：" + result[5] + "    反映後RP" + modifiedRP);
			}
		});
		btnCalc.setBounds(450, 246, 98, 26);
		contentPane.add(btnCalc);
	}
}
