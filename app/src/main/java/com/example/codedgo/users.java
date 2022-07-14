package com.example.codedgo;

public class users {

    public String username, email, coins;
        Integer easyscore , hardscore;
        public users (){
        }

        public users(String username, String email, Integer easyscore, Integer hardscore, String coins){
            this.username = username;
            this.email = email;
            this.easyscore = easyscore;
            this.hardscore = hardscore;
            this.coins = coins;
        }

    public String getUsername() {
        return username;
    }

    public Integer getEasyscore() {
        return easyscore;
    }

    public Integer getHardscore() {
        return hardscore;
    }
}

