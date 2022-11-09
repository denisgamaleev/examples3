import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {private static Logger logger;
    private static Logger errorLogger;
    private static Logger infoLogger;
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        logger= LogManager.getRootmanager();
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            if (tokens[0].equals("add")) {
                try {
                    infoLogger.info("Пользователь вызвал add");
                    executor.addCustomer(tokens[1]);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    errorLogger.error("не верный формат ввода : ");
                    System.out.println("не верный формат ввода : " + ADD_COMMAND);
                } catch (IllegalAccessException ex) {
                    errorLogger.error("не верный формат email : ");
                    System.out.println("не верный формат email: ");
                } catch (IllegalArgumentException ex) {
                    errorLogger.error("не верный формат phone : ");
                    System.out.println("не верный формат phone : ");
                } catch (Exception ex) {
                    errorLogger.error("Ошибка неизвестного формата");
                    System.out.println("Что то пошло не так");
                }
            } else if (tokens[0].equals("list")) {
                infoLogger.info("Пользователь вызвал list");
                executor.listCustomers();
            } else if (tokens[0].equals("remove")) {
                try {
                    infoLogger.info("Пользователь вызвал remove");
                    executor.removeCustomer(tokens[1]);
                }catch (ArrayIndexOutOfBoundsException ex){
                    errorLogger.error("Пользователь вызвал remove");
                    System.out.println("Укажите кого нужно удалить из списка");
                }
            } else if (tokens[0].equals("count")) {
                infoLogger.info("Пользователь вызвал count");
                System.out.println("There are " + executor.getCount() + " customers");
            } else if (tokens[0].equals("help")) {
                infoLogger.info("Пользователь вызвал help");
                System.out.println(helpText);
            } else {
                System.out.println(COMMAND_ERROR);
            }
        }
    }
}
