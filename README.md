# UserInfoManagementSystem
作者：计算机 1701　叶文滔　1711640118

- **Attention: All Source Code is encoded in UTF-8**
- To run this program, you should have installed `java 1.8.0 (or else)` on your system. If you want to run version 4.1, you should have another `MySQL 5.1` on your system.  

## 1. Product Manual
![pic](https://raw.githubusercontent.com/ttqf975414890/UserInfoManagementSystem/master/GUI_运行截图.png)  
UIMS is a simple `user information management system` for students, which can storage 4 columns of datas for a large quantity of students.

Instead of using the traditional *(task given)* GUI, this program simplified the GUI that brings you a better sense just like using a Excel table.

**Adding** a student is just an operation of `double-clicking on a new line then editing`. When the information is all written and correct, it will automatically save and add a new row.

**Editing** a student information is the same way as you add a student.

**Deleting** a student is just one `del` key on the row which you want to delete.

**Searching** student(s) is an operation of selecting the method (`按 ID`, `按姓名`, `按姓名（模糊）`) then input the word on the right input box. Filtered information and single information is shown on a same table, which can greatly reduce discomfort of switching panel. To **back to overall information**, clear the input box.

When you are operating, the status bar on the bottom of the window will prompt the status. Thus ***I don't make the log panel. It's so old-fasioned and ugly. My goal is bringing a better User Interface to the real user.***

All data will be saved on your MySQL server. So when you launch the program, it will `show you a CLI first` to ask you for the server IP address, port, username and password. When connection succeed, the GUI will be shown.

## 2. Module Structure Diagram
<img src="https://raw.githubusercontent.com/ttqf975414890/UserInfoManagementSystem/master/模块结构图_v4.1.svg">  

## 3. Program Flow Chart
<img src="https://raw.githubusercontent.com/ttqf975414890/UserInfoManagementSystem/master/程序流程图_v4.1.svg">  

## 4. About
- Project website: https://github.com/ttqf975414890/UserInfoManagementSystem  
- Author: 计算机 1701　叶文滔　1711640118  
- Author website: http://ttqf.tech  

## 5. Environments
The project is tested on the following platform:  
- Windows 10 Profressional 1803 (10.0.17134.1)  
- Eclipse Photon (4.8.0) (20180619-1200)  
- JDK version: 1.8.0_181

## 6. Update History
- ### 4.1 *(Student only)*
- Created executable `.jar` file. Now you don't need to compile it before run every time.
- Change data storage mode to MySQL database.
- More clear code structure. Now it's real pure V->C->M->C->V frame *(Except the entrance... It's still CLI and not on the "UIMS.view" package🙃)*. Deleted some unused code.
- ### 4.0 *(Student only)*
- New Graphical User Interface! *(Yeah I didn't do as the task given GUI like...)*
- ### 3.0
- Implements student and worker information autoload and autosave. These data will be saved as a `.csv` file on the project directory.
- ### 2.1.1
- Refactored the code to `Model-View` mode. Not have worked out how to add the `Controller`.
- Add new function `fuzzy search`.
- Tried many ways to call Windows API *(to play some music though the beeper 2333333)*. But none of them have worked. So cancelled🖕.
- ### 2.0
- Implements same function as 1.0.1 but use ArrayList instead of normal list.
- Change code coding to UTF-8.
- ### 1.0.1
- Implements student and worker information management. Now it have `adding`, `deleting`, `editing`, `searching by ID` and `searching by name` function. You can storage no more then 10 students' or workers' information.

Hope you have fun! ♨️
