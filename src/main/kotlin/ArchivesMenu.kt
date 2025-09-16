class ArchivesMenu(private val archives: MutableList<Archive>) : Menu(
    "УПРАВЛЕНИЕ АРХИВАМИ",
    mutableListOf()
) {
    init {
        updateMenuItems()
    }

    private fun updateMenuItems() {
        items.clear()
        items.add(MenuItem("Создать архив") { createArchive(); false })

        archives.forEachIndexed { index, archive ->
            items.add(MenuItem(archive.name) { openArchive(archive); false })
        }

        items.add(MenuItem("Выход") { true })
    }

    private fun createArchive() {
        print("Введите название архива: ")
        val name = readLine()?.trim() ?: ""

        if (name.isBlank()) {
            println("Название архива не может быть пустым")
            return
        }

        archives.add(Archive(name))
        updateMenuItems()
        println("Архив '$name' создан")
    }

    private fun openArchive(archive: Archive) {
        NotesMenu(archive).show()
        updateMenuItems()
    }
}