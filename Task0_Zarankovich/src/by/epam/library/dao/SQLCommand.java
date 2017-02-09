package by.epam.library.dao;

public final class SQLCommand {
	public static final String RENAME_BOOK = "UPDATE book SET b_name = ? WHERE id = ?";
	public static final String SHOW_ONE = "select employee.e_id, count(book.b_id) AS amount_book FROM lib.employee LEFT join lib.book ON employee.e_id = lib.book_has_employee group by employee.e_name having amount_book > 1";
	public static final String SHOW_TWO = "select employee.e_name, employee.e_date_of_birth, count(book.b_id) AS amount_book FROM lib.employee LEFT join lib.book ON employee.e_id = lib.book_has_employee group by employee.e_name having amount_book > 2";
	
}
