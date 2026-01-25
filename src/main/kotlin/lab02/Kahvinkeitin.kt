class Kahvinkeitin(
    private val vesiKapasiteetti: Int = 300,
    private val kahviKapasiteetti: Int = 100
) {

    // Keittimen tila
    private var paalla: Boolean = false

    // Säiliöiden nykyinen määrä
    private var vesi: Int = 0
    private var kahvi: Int = 0

    // Veden ja kahvin määrät voi lukea, mutta ei asettaa
    val vedenMaara: Int
        get() = vesi

    val kahvinMaara: Int
        get() = kahvi

    // Computed propertyt (ei varaa muistia)
    val espressoja: Int
        get() = minOf(vesi / 30, kahvi / 16)

    val tavallisia: Int
        get() = minOf(vesi / 200, kahvi / 15)

    // On / Off -nappi
    fun painaOnOffNappia() {
        paalla = !paalla
    }

    // Täyttömetodit
    fun taytaVesiSailio() {
        vesi = vesiKapasiteetti
    }

    fun taytaKahviSailio() {
        kahvi = kahviKapasiteetti
    }

    // Kahvin teko
    fun teeEspresso(): Boolean {
        if (!paalla) return false
        if (vesi < 30 || kahvi < 16) return false

        vesi -= 30
        kahvi -= 16
        return true
    }

    fun teeTavallinenKahvi(): Boolean {
        if (!paalla) return false
        if (vesi < 200 || kahvi < 15) return false

        vesi -= 200
        kahvi -= 15
        return true
    }

    // Tulostus
    override fun toString(): String {
        val tila = if (paalla) "on päällä" else "on kiinni"
        return "Keitin: $vesi/$vesiKapasiteetti vettä, $kahvi/$kahviKapasiteetti kahvia $tila"
    }
}
