package edu.hitsz.ranking;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao{
    //模拟数据库数据
    private List<User> users;
    public UserDaoImpl() throws IOException, ClassNotFoundException {
        System.out.println(1);
        users = new ArrayList<User>();
        String dir = "src/data/data.txt";
        FileInputStream fis = new FileInputStream(dir);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
        String name,time,score;
        User user;
        while((score = br.readLine())!=null){
            name = br.readLine();
            time = br.readLine();
            user = new User(Integer.parseInt(score),name,time);
            users.add(user);
        }
        br.close();

    }

    //获取所有图书
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    //新增用户
    @Override
    public void doAdd(User user) {
        users.add(user);
        Collections.sort(users);
    }

    @Override
    public void printRanking(){
        System.out.println("******************************");
        System.out.println("          得分排行榜           ");
        System.out.println("******************************");
        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            System.out.println("第"+(i+1)+"名："+user.getUserName()+","+user.getScore()+","+user.getTime());
        }
    }

    @Override
    public void update() throws IOException {
        FileOutputStream fos = new FileOutputStream("src/data/data.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
        for(User user: users){
            osw.write(Integer.toString(user.getScore()));
            osw.write(System.lineSeparator());
            osw.write(user.getUserName());
            osw.write(System.lineSeparator());
            osw.write(user.getTime());
            osw.write(System.lineSeparator());
        }
        osw.close();

    }
}
