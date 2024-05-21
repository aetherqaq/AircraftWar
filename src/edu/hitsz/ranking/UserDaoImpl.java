package edu.hitsz.ranking;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao{
    //模拟数据库数据
    private List<User> users;
    public UserDaoImpl(int gameLevel) throws IOException, ClassNotFoundException {
        System.out.println(1);
        users = new ArrayList<User>();
        String dir;
        if(gameLevel==1){
            dir = "src/data/EasyData.txt";
        }
        else if(gameLevel==2){
            dir = "src/data/NormalData.txt";
        }
        else{
            dir = "src/data/HardData.txt";
        }
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

    //获取所有用户
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    //新增用户
    @Override
    public void doAdd(User user) {
        users.add(user);
        Collections.sort(users);
        try {
            this.update();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void update() throws IOException {
        FileOutputStream fos = new FileOutputStream("src/data/NormalData.txt");
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

    @Override
    public void doDelete(int num){
        users.remove(users.get(num));
        try {
            this.update();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return;
    }
}
