package controller;

import controller.commands.*;


public enum CommandEnum {

    LOGIN{
        {
            this.command = new LoginCommand();
        }
    },

    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    },

    REGISTER{
        {
            this.command = new RegisterCommand();
        }
    },

    FILM_UPLOAD{
        {
            this.command = new FilmUploadCommand();
        }
    },

    GET_FILM_LIST{
        {
            this.command = new GetFilmListCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCommand() {
        return command;
    }
}
