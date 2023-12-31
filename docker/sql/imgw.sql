CREATE DATABASE imgw;
\c imgw;


CREATE TABLE weather_data (
    id_stacji INT,
    stacja VARCHAR(255),
    data_pomiaru DATE,
    godzina_pomiaru INT,
    temperatura FLOAT,
    predkosc_wiatru FLOAT,
    kierunek_wiatru FLOAT,
    wilgotnosc_wzgledna FLOAT,
    suma_opadu FLOAT,
    cisnienie FLOAT
);



