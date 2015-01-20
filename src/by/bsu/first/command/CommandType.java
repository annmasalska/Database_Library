package by.bsu.first.command;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    PRINT(new CatalogCommand()),
    ADDBOOK(new AddBookCommand()),
    DELETE(new DeleteBookCommand()),
    LOCALE(new LocaleCommand()),
    SELECTBYGENRE(new SelectBooksByGenreCommand()),
    SELECTORDERSBYUSERNAME(new SelectOrdersByUsername()),
    SELECTISSUEDBOOKS(new SelectIssuedBooks()),
    CONFIRMORDER(new ConfirmOrderCommand()),
    SEARCHBYAUTHOR(new SearchCommand()),
    RETURNBOOK(new ReturnBookCommand()),
    ORDER(new OrderCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
