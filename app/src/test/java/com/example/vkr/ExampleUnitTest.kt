//package com.example.vkr
//
//import com.example.vkr.user_interface.Model.Logtrening
//import com.example.vkr.user_interface.Model.User
//import com.nhaarman.mockitokotlin2.mock
//import com.nhaarman.mockitokotlin2.verify
//import com.nhaarman.mockitokotlin2.whenever
//import kotlinx.android.synthetic.main.fragment_avtorisation.*
//import org.junit.Test
//
//import org.junit.Assert.*
////интерфейсы для мокирования
//interface LoginPassword{
//    fun loginChek(login: String):Boolean
//    fun passwordChek(password: String):Boolean
//    fun lenghtChek(password: String):Boolean
//    fun doneAuf(login: String,password: String)
//}
//
//interface TitleData{
//    fun TitleChek(title: String):Boolean
//    fun lenghtChek(title: String):Boolean
//    fun DataChek(data: String):Boolean
//    fun doneAdd(title: String,data: String)
//}
//
////______________________________________________
//
////код который мокируется 1
//class chekLoginPassword(val loginPas: LoginPassword){
//
//    var Massiv_UsersTest = arrayListOf(
//        User("arkadiy","arkadiy","qwerty","65","170",true),
//        User("lexa","alex","123456","73","180",true)
//    )
//
//    fun chek(login:String,password:String){
//        if(loginPas.loginChek(login) && loginPas.passwordChek(password) && loginPas.lenghtChek(password))
//        {
//            var count=0
//            while(count< Massiv_UsersTest.size) {
//                if (password.length > 5 && password.length < 13) {
//                    if ((Massiv_UsersTest[count].login == login) && (Massiv_UsersTest[count].password == password)) {
//                        loginPas.doneAuf(login, password)
//                        break
//                    }
//                    count++
//                }
//            }
//        }
//        else
//        {
//            throw IllegalStateException("Пароль или логин введены неправильно")
//        }
//    }
//
//}
////___________________________________________________________________________________
////код который мокируется 2
//class chekLogTrening(val TitleData: TitleData){
//
//    var Massiv_UsersTestLogTrening = arrayListOf(
//        Logtrening("Бег на стадионе", "25.10.2020 15:30","20:00","невыполнено"),
//        Logtrening("Планка лежа дома", "25.10.2020 17:20","15:00","невыполнено")
//    )
//
//    fun chek(title: String,data:String){
//        if(TitleData.TitleChek(title) && TitleData.DataChek(data) && TitleData.lenghtChek(title))
//        {
//            var count=0
//            if (title.length > 5 && title.length < 20) {
//                while (count < Massiv_UsersTestLogTrening.size) {
//                    if ((Massiv_UsersTestLogTrening[count].titletrening != title) && (Massiv_UsersTestLogTrening[count].data != data)) {
//                        TitleData.doneAdd(title, data)
//                        break
//                    }
//                    count++
//                }
//            }
//        }
//        else
//        {
//            throw IllegalStateException("Уже запланировано такое действие или занята дата")
//        }
//    }
//
//}
//
////_____________________________________________________________________________________-
//
//
//
//class ExampleUnitTest {
//    //имитация бд для тестов
//    var Massiv_UsersTest = arrayListOf(
//        User("arkadiy","arkadiy56","password123","65","170",true),
//        User("lexa","alex","123456","73","180",true)
//    )
//
//    var Massiv_UsersTestLogTrening = arrayListOf(
//        Logtrening("Бег на стадионе", "25.10.2020 15:30","20:00","невыполнено"),
//        Logtrening("Планта лежа дома", "25.10.2020 17:20","15:00","невыполнено")
//    )
//
////________________________________________________________________
//
////код что тестируем методом базового пути
//        fun korrect_pass (password: String):Boolean{
//            var answer : Boolean = false
//            if (password.length > 5 && password.length < 13){
//                answer = true
//            }
//            else{
//                answer = false
//            }
//
//            return answer
//        }
//
//        fun korrect_login(login: String):Boolean{
//            var answer : Boolean = false
//            if (login.length > 5 && login.length < 13){
//                answer = true
//            }
//            else{
//                answer = false
//            }
//
//            return answer
//        }
//
//    fun isHaveToDB(login: String,password: String):Boolean{
//        var answer : Boolean = false
//        var count = 0
//        while(count< Massiv_UsersTest.size) {
//            if (password == Massiv_UsersTest[count].password && login == Massiv_UsersTest[count].login) {
//                answer = true
//                break
//            } else {
//                answer = false
//            }
//            count++
//        }
//        return answer
//    }
////___________________________________________________________________________
////код тестирование циклов то что тестим
//fun powerPass(password: String):Int {
//    var power: Int = 0
//    var count = 0
//    while (count < password.length) {
//        if (password[0]==password[0].toUpperCase()) {
//            if(password[count] == '!' || password[count] == '&' || password[count] == '$') {
//                power++
//            }
//        }
//        else{
//            break
//        }
//        count++
//    }
//    return power
//}
////______________________________________________
//
////код что тестим потоков данных
//fun doneAvtrorisation(login:String,password:String):Boolean{
//    var answer = true
//    return answer
//}
////___________________________________
////мокирование
//    @Test
//    fun mockirovanie1() {
//        val mockLoginPas:LoginPassword=mock()
//        whenever(mockLoginPas.loginChek("arkadiy")).thenReturn(true)
//        whenever(mockLoginPas.passwordChek("qwerty")).thenReturn(true)
//        whenever(mockLoginPas.lenghtChek("qwerty")).thenReturn(true)
//        val manager=chekLoginPassword(mockLoginPas)
//        manager.chek("arkadiy","qwerty")
//        verify(mockLoginPas).doneAuf("arkadiy","qwerty")
//    }
//    @Test
//    fun mockirovanie2() {
//        val mockTitleData:TitleData=mock()
//        whenever(mockTitleData.TitleChek("Отжимания дома")).thenReturn(true)
//        whenever(mockTitleData.lenghtChek("Отжимания дома")).thenReturn(true)
//        whenever(mockTitleData.DataChek("30.12.2020 15:00")).thenReturn(true)
//        val manager=chekLogTrening(mockTitleData)
//        manager.chek("Отжимания дома","30.12.2020 15:00")
//        verify(mockTitleData).doneAdd("Отжимания дома","30.12.2020 15:00")
//    }
////_________________________________________________________
////тест базового пути
//    @Test
//    fun basik_way_login(){
//        assertEquals(true, korrect_login("arkadiy56"))
//    }
//
//    @Test
//    fun basik_way_password(){
//        assertEquals(true, korrect_pass("password123"))
//    }
//
//    @Test
//    fun basik_way_ishavetodb(){
//        assertEquals(true, isHaveToDB("arkadiy56", "password123"))
//    }
////___________________________________________________________________________________
////тестирование поток данных
//    @Test
//    fun test_potokov_dannih1(){
//        assertEquals(true, doneAvtrorisation("arkadiy56", "password123"))
//    }
//    @Test
//    fun test_potokov_dannih2(){
//        assertEquals(true, doneAvtrorisation("admin", "admin"))
//    }
////______________________________________________________________________________
////тест циклов
//@Test
//fun test_ciklov(){
//    assertEquals(2, powerPass("Pa!ssw&prd"))
//}
////______________________________________________________________________________
//}