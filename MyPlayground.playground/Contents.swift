let driving = {
    print("i'm driving in my car")
}

driving()


let driving2 = { (place: String) in
    print("i'm going to \(place) in my car")
}

driving2("Moscow")

func pay(user: String, amount: Int){
    
}

let payment = {(user: String, amount: Int) in
    
}


let drivingWithReturn = { (place: String) -> String in
    return("i'm going to \(place) in my car")
}

let message = drivingWithReturn("Moscow")
print(message)
