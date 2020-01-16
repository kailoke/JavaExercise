package a2_PreparedStatement;

import org.junit.Test;

import a0_Bean.ExamStudent;
import a0_JDBCUtil.SQLUtil;
import java.util.ArrayList;
import java.util.Scanner;

// table: test/examStudent
public class A5_ExamStudentDBExercise {
    @Test
    // 接收控制台输入，插入新考生信息
    public void insertStuInfo() {
        // flowId,type,IDCard,ExamCard,StudentName,Location,Grade
        String sql = "insert into examStudent values (null,?,?,?,?,?,?)";
        Scanner scan = new Scanner(System.in);

        System.out.println("请输入考生详细信息");
        System.out.print("英语级别(4/6):"); int type = scan.nextInt();

        System.out.print("IdCard:"); String IdCard = scan.next();

        System.out.print("ExamCard:"); String ExamCard = scan.next();

        System.out.print("StudentName:"); String studentName = scan.next();

        System.out.print("Location:"); String Location = scan.next();

        System.out.print("Grade:"); int Grade = scan.nextInt();

        System.out.println();

        SQLUtil.update(sql,type,IdCard,ExamCard,studentName,Location,Grade);
    }

    @Test
    // 通过 IDCard || ExamCard查询考生信息
    public void queryStuInfo() {
        String sql = "select FlowID flowId,Type type,IDCard idCard,ExamCard examCard,StudentName studentName,Location location,Grade grade from examStudent where IDCard = ? or ExamCard = ?";
        Scanner scan = new Scanner(System.in);
        String exam = null;
        String idCard = null;
        // 获得控制台输入
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

        ArrayList<ExamStudent> query = SQLUtil.query(ExamStudent.class, sql, idCard, exam);
        query.forEach(System.out::println);
    }

    @Test
    public void delStuInfo(){
        Scanner scanner = new Scanner(System.in);
        String sql = "delete from examStudent where examCard = ?";

        while (true){
            System.out.print("删除---请输入学生考号：");
            String exam = scanner.next();
            int update = SQLUtil.update(sql, exam);

            if (update > 0){
                System.out.println("删除成功");
                break;
            }else
            {
                System.out.println("affected rows:0，请重新输入");
            }
        }
    }
}
