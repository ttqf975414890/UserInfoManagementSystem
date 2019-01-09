# UserInfoManagementSystem
作者：计算机 1701　叶文滔　1711640118

- **Attention: All Source Code is encoded in UTF-8**
- This project does NOT include excutable binary files.

## 1. Product Manual
![pic](http://backupserver.tencent.ttqf.tech/UIMS/UIMS4.0.0MainGUI.png)
UIMS is a simple `user information management system` for students, which can storage 4 columns of datas for tens of thousands students.

Instead of using the traditional GUI, this program simplified the GUI that brings you a better sense just like using a Excel table.

**Adding** a student is just an operation of `double-clicking on a new line then editing`. When the information is all written and correct, it will automatically save and add a new row.

**Editing** a student information is the same way as you add a student.

**Deleting** a student is just one `del` key on the row which you want to delete.

**Searching** student(s) is an operation of selecting the method (`按 ID`, `按姓名`, `按姓名（模糊）`) then input the word on the right input box. Filtered information and single information is shown on a same table, which can greatly reduce discomfort of switching panel. To **back to overall information**, clear the input box.

When you are operating, the status bar on the bottom of the window will prompt the status. Thus ***I don't make the log panel. It's so old-fasioned and ugly. My goal is bringing a better User Interface to the real user.***

All data will be saved on a file `studentData.csv`. Remember, it's a csv file. So **Don't add comma**(`,`) in your information.  
*I hope nobody's ID includes comma. :-)*

## 2. About
- Project website: https://github.com/ttqf975414890/UserInfoManagementSystem  
- Author: 计算机 1701　叶文滔　1711640118  
- Author website: http://ttqf.tech  

## 3. Environments
The project is tested on the following platform:  
- Windows 10 Profressional 1803 (10.0.17134.1)  
- Eclipse Photon (4.8.0) (20180619-1200)  
- JDK version: 1.8.0_181
