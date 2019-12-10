package controller;

import controller.commands.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand (HttpServletRequest request){
        ActionCommand command = new EmptyCommand();

        String action = request.getParameter("command");

        System.out.println("action " + action);

        if(action == null || action.isEmpty()){
            return command;
        }

        try{
            CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
            command = commandEnum.getCommand();
        }catch (IllegalArgumentException e){
            request.setAttribute("wrong_action", action + " command not found or wrong");
        }

         return command;
    }
}
