package by.bsu.first.command;

import by.bsu.first.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command  {
    String execute(HttpServletRequest request) throws CommandException;
}
