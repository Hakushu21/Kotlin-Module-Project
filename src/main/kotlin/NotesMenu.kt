class NotesMenu(private val archive: Archive) : Menu(
    "АРХИВ: ${archive.name.uppercase()}",
    mutableListOf()
) {
    init {
        updateMenuItems()
    }

    private fun updateMenuItems() {
        items.clear()
        items.add(MenuItem("Создать заметку") { createNote(); false })

        archive.notes.forEachIndexed { index, note ->
            items.add(MenuItem(note.title) { viewNote(note); false })
        }

        items.add(MenuItem("Назад") { true })
    }

    private fun createNote() {
        print("Введите название заметки: ")
        val title = readLine()?.trim() ?: ""

        if (title.isBlank()) {
            println("Название заметки не может быть пустым")
            return
        }

        print("Введите содержание заметки: ")
        val content = readLine()?.trim() ?: ""

        if (content.isBlank()) {
            println("Содержание заметки не может быть пустым")
            return
        }

        archive.addNote(Note(title, content))
        updateMenuItems()
        println("Заметка '$title' создана")
    }

    private fun viewNote(note: Note) {
        println("\n=== ${note.title.uppercase()} ===")
        println(note.content)
        println("\nНажмите Enter для возврата...")
        readLine()
    }
}