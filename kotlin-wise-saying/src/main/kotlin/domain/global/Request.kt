package domain.global

class Request(
    val input: String,
) {
    var actionName: String = ""
    val paramMap = mutableMapOf<String, String>()

    init {
        val inputBits = input.split("?", limit = 2)
        actionName = inputBits[0]

        if(inputBits.size == 2) {

        }
    }
}