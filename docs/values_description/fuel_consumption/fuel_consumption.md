
# Fuel consumption

## Introduction

Fuel consumption is not available from the ECU.

Instead, it will be calculated from :
+ Injected fuel quantity per stroke
+ Engine RPM
+ Road speed
+ Diesel density : 820 to 860 kg / m^3 (we'll use 0.840kg per liter)


## Calculus details

The step-by-step calculus is as follows:

+ kilograms_per_stroke equals the injection_quantity divided
  by 1,000,000 (mg to kg);
+ Liters_per_stroke equals kilograms_per_stroke divided by diesel_density;
+ Liters_per_rotation equals liters_per_stroke multiplied by 2.5 (5 cylinders firing every two rotations);
+ Liters_per_minute : liters_per_rotation multiplied by engine_rpm;
+ Liters_per_hour : liters_per_minute multiplied by 60;
+ Liters_per_km : liters_per_hour divided by speed_kmh;
+ Liters_per_100km : liters_per_km * 100.


This calculus can be simplified by using a multiplying factor calculated from
the constants. It will avoid many intermediate processing, thus improving the
application's performance.

The factor is obtained as follows:

~~~~~{txt}
             strokes_per_rotation * minutes_in_an_hour * 100km
factor  = --------------------------------------------------------
           miligrams_in_gram * grams_in_kilogram * diesel_density

             2.5 * 60 * 100         15000
        = --------------------- = --------
           1000 * 1000 * 0.840     840000

        = 0.017857143
~~~~~

Then the fuel consumption is obtained like this:
~~~~~{txt}
                      injection_quantity_per_stroke * factor * engine_RPMs
liters_per_100km    = ----------------------------------------------------
                                             speed_kmh
~~~~~
