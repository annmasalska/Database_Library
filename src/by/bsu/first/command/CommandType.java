package by.bsu.first.command;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    PRINT(new CatalogCommand()),
    ADDBOOK(new AddBookCommand()),
    DELETE(new DeleteBookCommand()),
    LOCALE(new LocaleCommand()),
    ADDREADER(new AddReaderCommand()),
    SELECTBYGENRE(new SelectBooksByGenreCommand()),
    SELECTBORROWINFOBYUSERNAME(new SelectBorrowInfoByUsername()),
    ADDBORROWINFO(new AddBorrowInfoCommand()),
    SEARCHBYAUTHOR(new SearchCommand()),
    ORDER(new OrderCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
