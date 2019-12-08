package a3_ExerciseExamStu;

import com.mysql.cj.protocol.a.NativeConstants;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Exercise {

    @Test
    // 接收控制台输入，插入新考生信息
    public void insertStuInfo() {
        String sql = "insert into examstudent values (null,?,?,?,?,?,?)";
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入考生详细信息");
        System.out.print("英语级别(4/6):");
        int type = scan.nextInt();
        System.out.print("IdCard:");
        String IdCard = scan.next();
        System.out.print("ExamCard:");
        String ExamCard = scan.next();
        System.out.print("StudentName:");
        String studentName = scan.next();
        System.out.print("Location:");
        String Location = scan.next();
        System.out.print("Grade:");
        int Grade = scan.nextInt();
        System.out.println();
        CommonUtil.update(sql,type,IdCard,ExamCard,studentName,Location,Grade);
    }

    @Test
    // 查询考生信息
    public void queryStuInfo() {
        String sql = "select FlowID flowId,Type type,IDCard idCard,ExamCard examCard,StudentName studentName,Location location,Grade grade from examstudent where IDCard = ? or ExamCard = ?";
        Scanner scan = new Scanner(System.in);
        String exam = null;
        String idCard = null;
        while (true){
            System.out.println("a:准考证号");
            System.out.println("b:身份证号");
            System.out.print("请选择查询类型：");
            String selection = scan.next();
            if ("a".equalsIgnoreCase(selection)){
                System.out.print("请输入准考证号:");
                exam  = scan.next();
                break;
            }else  if ("b".equalsIgnoreCase(selection)){
                System.out.print("请输入身份证号:");
                idCard = scan.next();
                break;
            }else {
                System.out.println("输入有误请重新输出！");
            }
        }
        System.out.println();
        ArrayList<ExamStudent> query = CommonUtil.query(ExamStudent.class, sql, idCard,exam);
        query.forEach(System.out::println);
    }

    @Test
    public void delStuInfo(){
        Scanner scanner = new Scanner(System.in);
        String sql = "delete from examStudent where examCard = ?";

        while (true){
            System.out.print("删除---请输入学生考号：");
            String exam = scanner.next();
            int update = CommonUtil.update(sql, exam);

            if (update > 0){
                System.out.println("删除成功");
                break;
            }else
            {
                System.out.println("查无此人，请重新输入");
            }
        }

    }
}


