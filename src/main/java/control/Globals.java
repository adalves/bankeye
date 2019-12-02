/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Jaison Robson Gusava
 */
public class Globals {
    public static boolean showDebugCommentaries = true;
    
    public static void comment(String comment) {
        if (showDebugCommentaries) {
            System.out.println("[Debug]: "+comment);
        }
    }
}
