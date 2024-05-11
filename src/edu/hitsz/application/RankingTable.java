package edu.hitsz.application;

import edu.hitsz.ranking.User;
import edu.hitsz.ranking.UserDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RankingTable {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTable scoreTable;
    private JButton deleteButton;
    private JScrollPane tableScrollPanel;
    private JLabel gameLevelLabel;

    public RankingTable(int score, int gameLevel){
        if(gameLevel==1){
            gameLevelLabel.setText("难度：EASY");
        }
        else if(gameLevel==2){
            gameLevelLabel.setText("难度：NORMAL");
        }
        else if(gameLevel==3){
            gameLevelLabel.setText("难度：HARD");
        }
        UserDaoImpl userDao;
        try {
            userDao = new UserDaoImpl();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String name = JOptionPane.showInputDialog(null, "游戏结束，你的得分为"+score+"\n请输入名字记录得分：");
        System.out.println(name);
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        String time = format.format(date);
        userDao.doAdd(new User(score,name,time));
        List<User> users = userDao.getAllUsers();

        String[] columnName = {"排名","玩家名","得分","记录时间"};
        String[][] tableData = new String[users.size()][4];
        for(int i=0;i<users.size();i++) {
            User user = users.get(i);
            tableData[i] = new String[]{Integer.toString(i+1), user.getUserName(), Integer.toString(user.getScore()), user.getTime()};
        }

        //表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        //JTable并不存储自己的数据，而是从表格模型那里获取它的数据
        scoreTable.setModel(model);
        tableScrollPanel.setViewportView(scoreTable);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = scoreTable.getSelectedRow();
                System.out.println(row);
                int result = JOptionPane.showConfirmDialog(deleteButton,
                        "是否确定中删除？");
                if (JOptionPane.YES_OPTION == result && row != -1) {
                    model.removeRow(row);
                    userDao.doDelete(row);
                }
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        UserDaoImpl UserDao;
        try {
            UserDao = new UserDaoImpl();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame("SimpleTable");
        frame.setContentPane(new RankingTable(100,2).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
