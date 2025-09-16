abstract class Menu(val title: String, val items: MutableList<MenuItem>) {

    fun show() {
        while (true) {
            println("\n$title")
            println("-".repeat(title.length))

            items.forEachIndexed { index, item ->
                println("$index. ${item.name}")
            }

            print("Выберите пункт меню: ")
            val input = readLine() ?: ""

            if (input.isBlank()) {
                println("Пожалуйста, введите номер пункта меню")
                continue
            }

            if (!input.all { it.isDigit() }) {
                println("Нужно ввести цифру, попробуйте еще раз")
                continue
            }

            val choice = input.toInt()
            if (choice < 0 || choice >= items.size) {
                println("Такого пункта нет, выберите от 0 до ${items.size - 1}")
                continue
            }

            val shouldExit = items[choice].action()
            if (shouldExit) {
                break
            }
        }
    }
}

data class MenuItem(val name: String, val action: () -> Boolean)