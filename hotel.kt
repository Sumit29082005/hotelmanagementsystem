import java.util.*

data class Room(
    val roomNumber: Int,
    var isBooked: Boolean = false,
    var customerName: String = "",
    var days: Int = 0
)

class HotelManagement {
    private val rooms = mutableListOf<Room>()

    init {
        for (i in 1..5) {
            rooms.add(Room(i))
        }
    }

    fun showRooms() {
        println("\n--- Room Status ---")
        for (room in rooms) {
            if (room.isBooked) {
                println("Room ${room.roomNumber}: Booked by ${room.customerName}")
            } else {
                println("Room ${room.roomNumber}: Available")
            }
        }
    }

    fun bookRoom(scanner: Scanner) {
        print("Enter Room Number: ")
        val roomNo = scanner.nextInt()

        val room = rooms.find { it.roomNumber == roomNo }

        if (room != null && !room.isBooked) {
            scanner.nextLine()
            print("Enter Customer Name: ")
            val name = scanner.nextLine()

            print("Enter Number of Days: ")
            val days = scanner.nextInt()

            room.isBooked = true
            room.customerName = name
            room.days = days

            println("Room Booked Successfully!")
        } else {
            println("Room not available!")
        }
    }

    fun checkout(scanner: Scanner) {
        print("Enter Room Number: ")
        val roomNo = scanner.nextInt()

        val room = rooms.find { it.roomNumber == roomNo }

        if (room != null && room.isBooked) {
            val ratePerDay = 2000
            val total = room.days * ratePerDay

            println("\n--- Bill ---")
            println("Customer: ${room.customerName}")
            println("Days: ${room.days}")
            println("Rate per day: ₹$ratePerDay")
            println("Total Bill: ₹$total")

            room.isBooked = false
            room.customerName = ""
            room.days = 0

            println("Checkout Done!")
        } else {
            println("Invalid Room!")
        }
    }
}

fun main() {
    val hotel = HotelManagement()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\n==== HOTEL MANAGEMENT SYSTEM ====")
        println("1. View Rooms")
        println("2. Book Room")
        println("3. Checkout")
        println("4. Exit")
        print("Enter choice: ")

        when (scanner.nextInt()) {
            1 -> hotel.showRooms()
            2 -> hotel.bookRoom(scanner)
            3 -> hotel.checkout(scanner)
            4 -> {
                println("Thank you!")
                break
            }
            else -> println("Invalid Choice!")
        }
    }
}