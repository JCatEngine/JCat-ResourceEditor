package JavaFxPlus.KotlinEx

infix fun Any.run(closure:(Any)->Unit)=closure(this)