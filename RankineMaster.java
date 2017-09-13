//The Java Abstract Windowing Toolkit
//run this program on web browser for best viewing
import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.URL;
import javax.imageio.ImageIO;

public class RankineMaster extends Applet implements ItemListener,ActionListener {
//Create a drop down list named textAlignment
 Choice process = new Choice();
 Choice work_fl=new Choice();
 Choice prob_type=new Choice();int probno=0;
 
 int tcount[]={0,0,0,0}; int pcount[]={0,0,0,0};int bcount[]={0,0};int ccount[]={0,0};
 int tc=0;int pc=0;int bc=0;int cc=0;
 boolean turb=true;boolean boil=false;
 boolean pump=false;boolean cond=false;
 Image aang1;Image aang2;Image aang3;Image aang4;Image aang5;Image aang6;
 int[] twf={-1,-1,-1,-1};int[] pwf={-1,-1,-1,-1};int[] cwf={-1,-1};int[] bwf={-1,-1};//whether process working fluid is water,mercury or other
 
Image img;
   MediaTracker tr;
String result;
double carnoteff=100;double rankineeff=100;double workratio=0;int diag=-1;

 
Panel p1= new Panel();
Panel p2=new Panel();

Panel start1=new Panel();
Panel start2=new Panel();

double[] steam_sat_t= {0.01,0.0061,1.0002,206.1,0.01,2376,0.01,2501,0,9.156,
                      4,0.0081,1.0001,157.2,16.79,2381,16.79,2509,0.061,9.051,
                      5,0.0087,1.0001,147.1,21.00,2383,21,2511,0.0762,9.026,
                      6,0.0093,1.0001,137.7,25.21,2384,25.21,2512,0.0912,9.000,
                      8,0.0107,1.0001,120.9,33.61,2387,33.61,2516,0.1212,8.950,
                      10,0.0123,1.0001,106.4,42.01,2389,42.01,2520,0.151,8.901,
                      11,0.0131,1.0007,99.86,46.19,2391,46.19,2522,0.1658,8.876,
                      12,0.0140,1.0007,93.79,50.40,2392,50.4,2523,0.1806,8.852,
                      13,0.0150,1.0007,88.13,54.59,2393,54.59,2525,0.1953,8.828,
                      14,0.0160,1.0007,82.85,58.80,2394,58.8,2527,0.2099,8.805,
                      15,0.0170,1.0007,77.93,62.99,2396,62.99,2529,0.2245,8.781,
                      16,0.0182,1.0013,73.34,67.17,2397,67.17,2531,0.239,8.758,
                      17,0.0194,1.0013,69.05,71.36,2399,71.36,2533,0.2535,8.735,
                      18,0.0206,1.0013,65.04,75.57,2400,75.57,2534,0.2679,8.712,
                      19,0.0220,1.0013,61.30,79.76,2401,79.76,2536,0.2823,8.690,
                      20,0.0234,1.002,57.79,83.94,2403,83.94,2538,0.2966,8.667,
                      21,0.0249,1.002,54.52,88.13,2404,88.13,2540,0.3108,8.645,
                      22,0.0264,1.002,51.45,92.32,2406,92.32,2542,0.3251,8.623,
                      23,0.0281,1.0026,48.58,96.50,2407,96.5,2544,0.3392,8.601,
                      24,0.0298,1.0026,45.89,100.7,2409,100.7,2545,0.3533,8.579,
                      25,0.0317,1.0032,43.36,104.9,2410,104.9,2547,0.3673,8.558,
                      26,0.0336,1.0032,41.00,109.0,2411,109.0,2549,0.3814,8.537,
                      27,0.0357,1.0032,38.78,113.2,2412,113.2,2551,0.3953,8.515,
                      28,0.0378,1.0038,36.69,117.4,2414,117.4,2553,0.4093,8.495,
                      29,0.0401,1.0038,34.73,121.6,2415,121.6,2554,0.4231,8.474,
                      30,0.0425,1.0045,32.90,125.8,2416,125.8,2556,0.4369,8.453,
                      31,0.0450,1.0045,31.17,130.0,2418,130.0,2558,0.4507,8.433,
                      32,0.0476,1.0051,29.54,134.1,2419,134.1,2560,0.4644,8.413,
                      33,0.0503,1.0051,28.01,138.3,2421,138.3,2562,0.478,8.393,
                      34,0.0532,1.0057,26.57,142.5,2422,142.5,2563,0.4917,8.373,
                      35,0.0563,1.0057,25.22,146.7,2423,146.7,2565,0.5053,8.353,
                      36,0.0595,1.0063,23.94,150.8,2425,150.8,2567,0.5188,8.333,
                      38,0.0663,1.007,21.60,159.2,2427,159.2,2571,0.5457,8.295,
                      40,0.0738,1.0076,19.52,167.5,2430,167.5,2574,0.5725,8.257,
                      45,0.0959,1.010,15.26,188.4,2437,188.4,2583,0.6386,8.165,
                      50,0.1235,1.012,12.03,209.3,2443,209.3,2592,0.7037,8.076,
                      55,0.1576,1.015,9.569,230.2,2450,230.2,2601,0.7679,7.991,
                      60,0.1994,1.017,7.671,251.1,2457,251.1,2610,0.8311,7.910,
                      65,0.2503,1.020,6.197,272.0,2463,272.0,2618,0.8934,7.831,
                      70,0.3119,1.023,5.042,293.0,2470,293.0,2627,0.9549,7.755,
                      75,0.3858,1.026,4.131,313.9,2476,313.9,2635,1.016,7.682,
                      80,0.4739,1.029,3.407,334.8,2482,334.9,2644,1.075,7.612,
                      85,0.5783,1.033,2.828,355.8,2488,355.9,2652,1.134,7.544,
                      90,0.7013,1.036,2.361,376.8,2494,376.9,2660,1.193,7.479,
                      95,0.8455,1.039,1.982,397.9,2501,398.0,2668,1.250,7.416,
                      100,1.013,1.044,1.673,418.9,2507,419.0,2676,1.307,7.355,
                      110,1.433,1.052,1.21,461.1,2518,461.3,2691,1.418,7.239,
                      120,1.985,1.060,0.892,503.5,2529,503.7,2706,1.528,7.130,
                      130,2.701,1.069,0.669,546.0,2540,546.3,2720,1.634,7.027,
                      140,3.613,1.080,0.509,588.7,2550,589.1,2734,1.739,6.930,
                      150,4.758,1.091,0.393,631.7,2559,632.2,2746,1.842,6.838,
                      160,6.178,1.102,0.307,674.9,2568,675.5,2758,1.943,6.750,
                      170,7.916,1.114,0.243,718.3,2576,719.2,2769,2.042,6.666,
                      180,10.02,1.127,0.194,762.1,2584,763.2,2778,2.140,6.586,
                      190,12.54,1.141,0.157,806.2,2589,807.6,2786,2.236,6.508,
                      200,15.54,1.156,0.127,850.6,2596,852.4,2793,2.331,6.432,
                      210,19.06,1.172,0.104,895.5,2600,897.8,2798,2.425,6.358,
                      220,23.18,1.190,0.086,940.8,2603,943.6,2802,2.518,6.286,
                      230,27.95,1.209,0.072,986.7,2603,990.1,2804,2.610,6.215,
                      240,33.44,1.229,0.06,1033,2603,1037.3,2804,2.702,6.144,
                      250,39.73,1.251,0.05,1080,2603,1085.3,2802,2.793,6.073,
                      260,46.88,1.275,0.042,1128,2600,1134.4,2797,2.884,6.002,
                      270,54.98,1.302,0.036,1177,2592,1184.5,2790,2.975,5.930,
                      280,64.11,1.332,0.03,1227,2587,1236.0,2780,3.067,5.857,
                      290,74.36,1.365,0.026,1279,2573,1289.0,2766,3.159,5.782,
                      300,85.81,1.403,0.022,1332,2560,1344.0,2749,3.253,5.704,
                      320,112.7,1.499,0.015,1445,2531,1461.5,2700,3.448,5.536,
                      340,145.9,1.638,0.011,1570,2462,1594.1,2622,3.659,5.336,
                      360,186.5,1.893,0.007,1725,2351,1760.5,2481,3.915,5.053,
                      374.14,220.9,3.155,0.003155,2030,2030,2099.3,2099,4.430,4.430};
                      
double[] steam_sat_p={0.04,28.96,1.004,34.80,121.4,2415,121.4,2554,0.423,8.475,
0.06,36.15,1.006,23.75,151.5,2425,151.5,2567,0.521,8.331,
0.08,41.5,1.008,18.11,173.8,2432,173.8,2577,0.593,8.229,
0.1,45.8,1.010,14.68,191.8,2438,191.8,2585,0.649,8.150,
0.2,60.07,1.017,7.649,251.4,2457,251.4,2610,0.832,7.908,
0.3,69.11,1.023,5.229,289.2,2468,289.2,2625,0.944,7.769,
0.4,75.87,1.026,3.994,317.5,2477,317.6,2637,1.026,7.670,
0.5,81.33,1.030,3.240,340.4,2484,340.5,2646,1.091,7.594,
0.6,85.94,1.033,2.732,359.8,2490,359.9,2653,1.145,7.532,
0.7,89.95,1.036,2.365,376.6,2494,376.7,2660,1.192,7.480,
0.8,93.5,1.039,2.087,391.6,2499,391.7,2666,1.233,7.435,
0.9,96.71,1.041,1.870,405.1,2503,405.1,2671,1.270,7.395,
1,99.62,1.043,1.694,417.3,2506,417.4,2675,1.303,7.359,
1.5,111.4,1.053,1.159,466.9,2520,467.1,2694,1.434,7.223,
2,120.2,1.061,0.886,504.5,2530,504.7,2707,1.530,7.127,
3,133.6,1.073,0.606,561.1,2544,561.5,2725,1.672,6.992,
4,143.6,1.084,0.463,604.3,2554,604.8,2739,1.777,6.896,
5,151.9,1.093,0.375,639.7,2561,640.2,2749,1.861,6.821,
6,158.9,1.101,0.316,669.9,2567,670.6,2757,1.931,6.760,
7,165.0,1.108,0.273,696.4,2573,697.2,2764,1.992,6.708,
8,170.4,1.115,0.240,720.2,2577,721.1,2769,2.046,6.663,
9,175.4,1.121,0.215,741.8,2580,742.8,2774,2.095,6.623,
10,179.9,1.127,0.194,761.7,2584,762.8,2778,2.139,6.586,
20,212.4,1.177,0.100,906.4,2600,908.8,2800,2.447,6.341,
30,233.9,1.217,0.067,1005,2604,1008,2804,2.646,6.187,
40,250.4,1.252,0.050,1082,2602,1087,2801,2.796,6.070,
50,264.0,1.286,0.039,1148,2597,1154,2794,2.920,5.973,
60,275.6,1.319,0.032,1205,2590,1213,2784,3.027,5.889,
70,285.9,1.352,0.027,1258,2580,1267,2772,3.121,5.813,
80,295.1,1.384,0.024,1306,2570,1317,2758,3.207,5.743,
90,303.4,1.418,0.021,1350,2558,1363,2742,3.286,5.677,
100,311.1,1.453,0.018,1393,2545,1408,2725,3.360,5.614,
110,318.2,1.489,0.016,1434,2530,1450,2706,3.429,5.553,
120,324.8,1.527,0.014,1473,2513,1491,2685,3.496,5.492,
130,331.0,1.567,0.013,1511,2496,1532,2662,3.561,5.432,
140,336.8,1.611,0.012,1549,2477,1571,2638,3.623,5.372,
150,342.3,1.658,0.010,1586,2456,1611,2611,3.685,5.310,
160,347.4,1.711,0.009,1623,2432,1650,2581,3.746,5.246,
170,352.4,1.770,0.008,1660,2405,1690,2547,3.808,5.178,
180,357.0,1.839,0.008,1699,2375,1732,2510,3.871,5.105,
190,361.5,1.924,0.007,1740,2338,1776,2465,3.938,5.024,
200,365.8,2.036,0.006,1786,2295,1826,2411,4.013,4.931,
220.9,374.1,3.155,0.003,2030,2029,2099,2099,4.430,4.430};

double[] supersteam={0.10,45.8,14.67,2437.2,2583.9,8.149,50,14.87,2443.3,2592.0,8.174,100,17.20,2515.5,2687.5,8.449,150,19.51,2587.9,2783.0,8.689,200,21.83,2661.3,2879.6,8.905,250,24.14,2736.1,2977.4,9.102,300,26.45,2812.3,3076.7,9.283,350,28.76,2890.0,3177.5,9.451,400,31.06,2969.3,3279.,9.609,450,33.37,3050.3,3384.0,9.758,500,35.68,3132.9,3489.7,9.900,600,40.30,3303.3,3706.3,10.163,700,44.91,3480.8,3929.9,10.406,800,49.53,3665.3,4160.6,10.631,900,54.14,3856.9,4398.3,10.843,1000,58.76,4055.2,4642.8,11.043,
0.50,81.3,3.240,2483.2,2645.2,7.593,50,-1,-1,-1,-1,100,3.419,2511.5,2682.4,7.695,150,3.890,2585.7,2780.2,7.941,200,4.356,2660.0,2877.8,8.159,250,4.821,2735.1,2976.1,8.357,300,5.284,2811.6,3075.8,8.539,350,5.747,2889.4,3176.8,8.708,400,6.209,2968.9,3279.3,8.866,450,6.672,3049.,3383.5,9.015,500,7.134,3132.6,3489.3,9.157,600,8.058,3303.1,3706.0,9.420,700,8.981,3480.6,3929.7,9.663,800,9.905,3665.2,4160.4,9.888,900,10.828,3856.8,4398.2,10.100,1000,11.751,4055.1,4642.7,10.300,
1,99.6,1.694,2505.6,2674.9,7.359,50,-1,-1,-1,-1,100,1.696,2506.2,2675.8,7.361,150,1.937,2582.9,2776.6,7.615,200,2.172,2658.2,2875.5,7.836,250,2.406,2733.9,2974.5,8.035,300,2.639,2810.6,3074.5,8.217,350,2.871,2888.7,3175.8,8.387,400,3.103,2968.3,3278.6,8.545,450,3.334,3049.4,3382.8,8.695,500,3.566,3132.2,3488.7,8.836,600,4.028,3302.8,3705.6,9.100,700,4.490,3480.4,3929.4,9.342,800,4.952,3665.0,4160.2,9.568,900,5.414,3856.6,4398.0,9.780,1000,5.875,4055.0,4642.6,9.980,
2,120.2,0.8857,2529.1,2706.2,7.127,150,0.9599,2577.1,2769.1,7.281,200,1.0805,2654.6,2870.7,7.508,250,1.1989,2731.4,2971.2,7.710,300,1.3162,2808.8,3072.1,7.894,350,1.4330,2887.3,3173.9,8.064,400,1.5493,2967.1,3277.0,8.224,450,1.6655,3048.5,3381.6,8.373,500,1.7814,3131.4,3487.7,8.515,600,2.0130,3302.2,3704.8,8.779,700,2.2443,3479.9,3928.8,9.022,800,2.4755,3664.7,4159.8,9.248,900,2.7066,3856.3,4397.6,9.460,1000,2.9375,4054.8,4642.3,9.660,
3,133.5,0.6058,2543.2,2724.9,6.992,150,0.6340,2571.0,2761.2,7.079,200,0.7164,2651.0,2865.9,7.313,250,0.7964,2728.9,2967.9,7.518,300,0.8753,2807.0,3069.6,7.704,350,0.9536,2885.9,3172.0,7.875,400,1.0315,2966.0,3275.5,8.035,450,1.1092,3047.5,3380.3,8.185,500,1.1867,3130.6,3486.6,8.327,600,1.3414,3301.6,3704.0,8.591,700,1.4958,3479.5,3928.2,8.834,800,1.6500,3664.3,4159.3,9.060,900,1.8042,3856.0,4397.3,9.272,1000,1.9582,4054.5,4642.0,9.473,
4,143.6,0.4624,2553.1,2738.1,6.896,150,0.4709,2564.4,2752.8,6.931,200,0.5343,2647.2,2860.9,7.172,250,0.5952,2726.4,2964.5,7.380,300,0.6549,2805.1,3067.1,7.568,350,0.7140,2884.4,3170.0,7.740,400,0.7726,2964.9,3273.9,7.900,450,0.8311,3046.6,3379.0,8.051,500,0.8894,3129.8,3485.5,8.193,600,1.0056,3301.0,3703.2,8.458,700,1.1215,3479.0,3927.6,8.701,800,1.2373,3663.9,4158.8,8.927,900,1.3530,3855.7,4396.9,9.139,1000,1.4686,4054.3,4641.7,9.340,
5,151.8,0.3748,2560.7,2748.1,6.821,200,0.4250,2643.3,2855.8,7.061,250,0.4744,2723.8,2961.0,7.272,300,0.5226,2803.2,3064.6,7.461,350,0.5702,2883.0,3168.1,7.635,400,0.6173,2963.7,3272.3,7.796,450,0.6642,3045.6,3377.7,7.947,500,0.7109,3129.0,3484.5,8.089,600,0.8041,3300.4,3702.5,8.354,700,0.8970,3478.5,3927.0,8.598,800,0.9897,3663.6,4158.4,8.824,900,1.0823,3855.4,4396.6,9.036,1000,1.1748,4054.0,4641.4,9.236,
6,158.8,0.3156,2566.8,2756.1,6.759,200,0.3521,2639.3,2850.6,6.968,250,0.3939,2721.2,2957.6,7.183,300,0.4344,2801.4,3062.0,7.374,350,0.4743,2881.6,3166.1,7.548,400,0.5137,2962.5,3270.8,7.710,450,0.5530,3044.7,3376.5,7.861,500,0.5920,3128.2,3483.4,8.004,600,0.6698,3299.8,3701.7,8.270,700,0.7473,3478.1,3926.4,8.513,800,0.8246,3663.2,4157.9,8.740,900,0.9018,3855.1,4396.2,8.952,1000,0.9789,4053.7,4641.1,9.152,
8,170.4,0.2403,2576.0,2768.3,6.662,200,0.2609,2631.0,2839.7,6.818,250,0.2932,2715.9,2950.4,7.040,300,0.3242,2797.5,3056.9,7.235,350,0.3544,2878.6,3162.2,7.411,400,0.3843,2960.2,3267.6,7.573,450,0.4139,3042.8,3373.9,7.726,500,0.4433,3126.6,3481.3,7.869,600,0.5019,3298.7,3700.1,8.135,700,0.5601,3477.2,3925.3,8.379,800,0.6182,3662.4,4157.0,8.606,900,0.6762,3854.5,4395.5,8.819,1000,0.7341,4053.2,4640.5,9.019,
10,179.9,0.1944,2582.7,2777.1,6.585,200,0.2060,2622.2,2828.3,6.696,250,0.2328,2710.4,2943.1,6.927,300,0.2580,2793.6,3051.6,7.125,350,0.2825,2875.7,3158.2,7.303,400,0.3066,2957.9,3264.5,7.467,450,0.3305,3040.9,3371.3,7.620,500,0.3541,3125.0,3479.1,7.764,600,0.4011,3297.5,3698.6,8.031,700,0.4478,3476.2,3924.1,8.276,800,0.4944,3661.7,4156.1,8.502,900,0.5408,3853.9,4394.8,8.715,1000,0.5872,4052.7,4639.9,8.916,
12,188.0,0.1633,2587.8,2783.7,6.522,200,0.1693,2612.9,2816.1,6.591,250,0.192,42704.7,2935.6,6.831,300,0.2139,2789.7,3046.3,7.034,350,0.2346,2872.7,3154.2,7.214,400,0.2548,2955.5,3261.3,7.379,450,0.2748,3038.9,3368.7,7.533,500,0.2946,3123.4,3476.9,7.678,600,0.3339,3296.3,3697.0,7.946,700,0.3730,3475.3,3922.9,8.190,800,0.4118,3661.0,4155.2,8.418,900,0.4506,3853.3,4394.0,8.630,1000,0.4893,4052.2,4639.4,8.831,
14,195.0,0.1408,2591.8,2788.8,6.468,200,0.1430,2602.7,2803.0,6.498,250,0.1636,2698.9,2927.9,6.749,300,0.1823,2785.7,3040.9,6.955,350,0.2003,2869.7,3150.1,7.138,400,0.2178,2953.1,3258.1,7.305,450,0.2351,3037.0,3366.1,7.459,500,0.2522,3121.8,3474.8,7.605,600,0.2860,3295.1,3695.4,7.873,700,0.3195,3474.4,3921.7,8.118,800,0.3529,3660.2,4154.3,8.346,900,0.3861,3852.7,4393.3,8.559,1000,0.4193,4051.7,4638.8,8.759,
16,201.4,0.1237,2594.8,2792.8,6.420,225,0.1329,2645.1,2857.8,6.554,250,0.1419,2692.9,2919.9,6.675,300,0.1587,2781.6,3035.4,6.886,350,0.1746,2866.6,3146.0,7.071,400,0.1901,2950.7,3254.9,7.239,450,0.2053,3035.0,3363.5,7.395,500,0.2203,3120.1,3472.6,7.541,600,0.2500,3293.9,3693.9,7.810,700,0.2794,3473.5,3920.5,8.056,800,0.3087,3659.5,4153.3,8.283,900,0.3378,3852.1,4392.6,8.497,1000,0.3669,4051.2,4638.2,8.697,
18,207.1,0.1104,2597.2,2795.9,6.378,225,0.1168,2637.0,2847.2,6.482,250,0.1250,2686.7,2911.7,6.609,300,0.1403,2777.4,3029.9,6.825,350,0.1546,2863.6,3141.8,7.012,400,0.1685,2948.3,3251.6,7.181,450,0.1821,3033.1,3360.9,7.338,500,0.1955,3118.5,3470.4,7.485,600,0.2220,3292.7,3692.3,7.754,700,0.2482,3472.6,3919.4,8.000,800,0.2743,3658.8,4152.4,8.228,900,0.3002,3851.5,4391.9,8.442,1000,0.3261,4050.7,4637.6,8.643,
20,212.4,0.0996,2599.1,2798.3,6.339,225,0.1038,2628.5,2836.1,6.416,250,0.1115,2680.2,2903.2,6.548,300,0.1255,2773.2,3024.2,6.768,350,0.1386,2860.5,3137.7,6.958,400,0.1512,2945.9,3248.3,7.129,450,0.1635,3031.1,3358.2,7.287,500,0.1757,3116.9,3468.2,7.434,600,0.1996,3291.5,3690.7,7.704,700,0.2233,3471.6,3918.2,7.951,800,0.2467,3658.0,4151.5,8.179,900,0.2701,3850.9,4391.1,8.393,1000,0.2934,4050.2,4637.0,8.594,
25,224.0,0.0800,2602.1,2801.9,6.256,250,0.0871,2663.3,2880.9,6.411,300,0.0989,2762.2,3009.6,6.646,350,0.1098,2852.5,3127.0,6.842,400,0.1201,2939.8,3240.1,7.017,450,0.1302,3026.2,3351.6,7.177,500,0.1400,3112.8,3462.7,7.325,600,0.1593,3288.5,3686.8,7.598,700,0.1784,3469.3,3915.2,7.846,800,0.1972,3656.2,4149.2,8.074,900,0.2160,3849.4,4389.3,8.288,1000,0.2347,4048.9,4635.6,8.490,
30,233.9,0.0667,2603.2,2803.2,6.186,250,0.0706,2644.7,2856.5,6.289,300,0.0812,2750.8,2994.3,6.541,350,0.0906,2844.4,3116.1,6.745,400,0.0994,2933.5,3231.7,6.923,450,0.1079,3021.2,3344.8,7.086,500,0.1162,3108.6,3457.2,7.236,600,0.1325,3285.5,3682.8,7.510,700,0.1484,3467.0,3912.2,7.759,800,0.1642,3654.3,4146.9,7.989,900,0.1799,3847.9,4387.5,8.203,1000,0.1955,4047.7,4634.1,8.405,
35,242.6,0.0571,2602.9,2802.6,6.124,250,0.0588,2624.0,2829.7,6.176,300,0.0685,2738.8,2978.4,6.448,350,0.0768,2836.0,3104.8,6.660,400,0.0846,2927.2,3223.2,6.843,450,0.0920,3016.1,3338.0,7.007,500,0.0992,3104.5,3451.6,7.159,600,0.1133,3282.5,3678.9,7.436,700,0.1270,3464.7,3909.3,7.685,800,0.1406,3652.5,4144.6,7.916,900,0.1541,3846.4,4385.7,8.130,1000,0.1675,4046.4,4632.7,8.332,
40,250.4,0.0498,2601.7,2800.8,6.070,275,0.0546,2668.9,2887.3,6.231,300,0.0589,2726.2,2961.7,6.364,350,0.0665,2827.4,3093.3,6.584,400,0.0734,2920.7,3214.5,6.771,450,0.0800,3011.0,3331.2,6.939,500,0.0864,3100.3,3446.0,7.092,600,0.0989,3279.4,3674.9,7.371,700,0.1110,3462.4,3906.3,7.621,800,0.1229,3650.6,4142.3,7.852,900,0.1348,3844.8,4383.9,8.067,1000,0.1465,4045.1,4631.2,8.270,
45,257.4,0.0441,2599.7,2797.9,6.020,275,0.0473,2651.3,2864.3,6.143,300,0.0514,2713.0,2944.2,6.285,350,0.0584,2818.6,3081.5,6.515,400,0.0648,2914.2,3205.6,6.707,450,0.0708,3005.8,3324.2,6.877,500,0.0765,3096.0,3440.4,7.032,600,0.0877,3276.4,3670.9,7.313,700,0.0985,3460.0,3903.3,7.565,800,0.1092,3648.8,4140.0,7.796,900,0.1197,3843.3,4382.1,8.012,1000,0.1302,4043.9,4629.8,8.214,
50,263.9,0.0395,2597.0,2794.2,5.974,275,0.0414,2632.3,2839.5,6.057,300,0.0454,2699.0,2925.7,6.211,350,0.0520,2809.5,3069.3,6.452,400,0.0578,2907.5,3196.7,6.648,450,0.0633,3000.6,3317.2,6.821,500,0.0686,3091.7,3434.7,6.978,600,0.0787,3273.3,3666.8,7.261,700,0.0885,3457.7,3900.3,7.514,800,0.0982,3646.9,4137.7,7.746,900,0.1077,3841.8,4380.2,7.962,1000,0.1172,4042.6,4628.3,8.165,
60,275.6,0.0325,2589.9,2784.6,5.890,300,0.0362,2668.4,2885.5,6.070,350,0.0423,2790.4,3043.9,6.336,400,0.0474,2893.7,3178.2,6.543,450,0.0522,2989.9,3302.9,6.722,500,0.0567,3083.1,3423.1,6.883,600,0.0653,3267.2,3658.7,7.169,700,0.0736,3453.0,3894.3,7.425,800,0.0817,3643.2,4133.1,7.658,900,0.0896,3838.8,4376.6,7.875,1000,0.0976,4040.1,4625.4,8.079,
70,285.8,0.0274,2581.0,2772.6,5.815,300,0.0295,2633.5,2839.9,5.934,350,0.0353,2770.1,3016.9,6.230,400,0.0400,2879.5,3159.2,6.450,450,0.0442,2979.0,3288.3,6.635,500,0.0482,3074.3,3411.4,6.800,600,0.0557,3260.9,3650.6,7.091,700,0.0629,3448.3,3888.2,7.349,800,0.0699,3639.5,4128.4,7.584,900,0.0768,3835.7,4373.0,7.801,1000,0.0836,4037.5,4622.5,8.006,
80,295.0,0.0235,2570.5,2758.7,5.745,300,0.0243,2592.3,2786.5,5.794,350,0.0300,2748.3,2988.1,6.132,400,0.0343,2864.6,3139.4,6.366,450,0.0382,2967.8,3273.3,6.558,500,0.0418,3065.4,3399.5,6.727,600,0.0485,3254.7,3642.4,7.022,700,0.0548,3443.6,3882.2,7.282,800,0.0610,3635.7,4123.8,7.518,900,0.0671,3832.6,4369.3,7.737,1000,0.0731,4035.0,4619.6,7.942};

double[] merc={0.001,121.94,0.0767,169.22,-1,-1,16.91,325.48,0.0507,0.8365,
0.002,137.24,0.0769,87.88,-1,-1,19.01,327.16,0.0561,0.8122,
0.004,154.28,0.0771,45.74,-1,-1,21.27,328.96,0.0620,0.7871,
0.006,164.79,0.0773,31.23,-1,-1,22.82,330.21,0.0649,0.7720,
0.008,172.35,0.0774,23.82,-1,-1,23.82,331.05,0.0674,0.7624,
0.010,178.39,0.0775,19.32,-1,-1,24.70,331.76,0.0695,0.7545,
0.02,198.88,0.07770,10.08,-1,-1,27.46,333.77,0.0758,0.7302,
0.04,221.16,0.07800,5.280,-1,-1,30.56,336.32,0.0825,0.7063,
0.06,235.45,0.07820,3.620,-1,-1,32.66,337.96,0.0862,0.6921,
0.08,245.76,0.07840,2.770,-1,-1,34.08,339.17,0.0888,0.6820,
0.1,254.54,0.078500,2.252,-1,-1,35.21,340.09,0.0908,0.6745,
0.2,282.77,0.078900,1.186,-1,-1,39.10,343.15,0.0984,0.6510,
0.3,300.20,0.079100,0.815,-1,-1,41.53,345.12,0.1030,0.6376,
0.4,314.07,0.079300,0.626,-1,-1,43.46,346.62,0.1063,0.6280,
0.5,325.09,0.079500,0.510,-1,-1,45.05,347.92,0.1088,0.6205,
0.6,334.47,0.079600,0.431,-1,-1,46.35,348.97,0.1109,0.6142,
0.7,342.53,0.079700,0.374,-1,-1,47.48,349.81,0.1130,0.6096,
0.8,349.46,0.079800,0.331,-1,-1,48.40,350.14,0.1147,0.6054,
0.9,356.08,0.079900,0.298,-1,-1,49.36,351.31,0.1160,0.6012,
1.0,362.00,0.080000,0.270,-1,-1,50.20,351.98,0.1172,0.5079,
1.2,372.50,0.080200,0.2293,-1,-1,51.71,352.78,0.1193,0.5916,
1.4,381.88,0.080300,0.1991,-1,-1,52.84,354.24,0.1218,0.5870,
1.6,389.43,0.080400,0.1764,-1,-1,53.97,354.96,0.1239,0.5832,
1.8,396.97,0.080500,0.1586,-1,-1,55.06,355.84,0.1256,0.5794,
2.0,403.60,0.080600,0.1442,-1,-1,55.98,356.55,0.1269,0.5761,
3.0,430.73,0.081000,0.0998,-1,-1,59.95,359.69,0.1319,0.5635,
4.0,451.12,0.081400,0.0771,-1,-1,62.97,362.03,0.1361,0.5539,
4.5,459.89,0.081500,0.0693,-1,-1,64.18,362.99,0.1377,0.5501,
5.0,467.95,0.081700,0.0631,-1,-1,65.31,363.87,0.1394,0.5472,
6.0,482.12,0.081900,0.0536,-1,-1,67.36,365.42,0.1423,0.5422,
7.0,467.13,0.082100,0.0467,-1,-1,69.16,366.76,0.1449,0.5372,
8.0,506.08,0.082200,0.0414,-1,-1,70.84,368.02,0.1469,0.5334,
9.0,516.28,0.082400,0.0373,-1,-1,72.31,369.15,0.1490,0.5296,
10.0,525.66,0.08250,0.0340,-1,-1,73.64,370.15,0.1507,0.5267};

Panel pli0=new Panel(); Panel pli5=new Panel();Panel pli10=new Panel();
Panel plo0=new Panel(); Panel plo5=new Panel();Panel plo10=new Panel();
Panel pli1=new Panel(); Panel pli6=new Panel();Panel pli11=new Panel();
Panel plo1=new Panel(); Panel plo6=new Panel();Panel plo11=new Panel();
Panel pli2=new Panel(); Panel pli7=new Panel();Panel pli12=new Panel();
Panel plo2=new Panel(); Panel plo7=new Panel();Panel plo12=new Panel();
Panel pli3=new Panel(); Panel pli8=new Panel();
Panel plo3=new Panel(); Panel plo8=new Panel();
Panel pli4=new Panel(); Panel pli9=new Panel();
Panel plo4=new Panel(); Panel plo9=new Panel();

Panel pi0=new Panel(); Panel pi4=new Panel();Panel pi8=new Panel();
Panel pi1=new Panel(); Panel pi5=new Panel();Panel pi9=new Panel();
Panel pi2=new Panel(); Panel pi6=new Panel();Panel pi10=new Panel();
Panel pi3=new Panel(); Panel pi7=new Panel();Panel pi11=new Panel();


Panel po0=new Panel(); Panel po4=new Panel();Panel po8=new Panel();
Panel po1=new Panel(); Panel po5=new Panel();Panel po9=new Panel();
Panel po2=new Panel(); Panel po6=new Panel();Panel po10=new Panel();
Panel po3=new Panel(); Panel po7=new Panel();Panel po11=new Panel();




Label lb1=new Label("        INLET");
Label lb2=new Label("        OUTLET");
Label lb3=new Label("        VALUE");
Label lb4=new Label("        VALUE");
//labels for turbine
Label[][] states=new Label[24][7];

Button create = new Button("CREATE/SHOW");
Button calculate=new Button("CALCULATE");
Button diagram=new Button("DIAGRAM");

Button turbineb0 = new Button("TURBINE 1(hide)"); Button pumpb0 = new Button("PUMP 1(hide)"); Button boilb0=new Button("BOILER 1(hide)");
Button resett0 = new Button("RESET");           Button resetp0 = new Button("RESET");         Button resetb0 = new Button("RESET");
Button turbineb1 = new Button("TURBINE 2(hide)"); Button pumpb1 = new Button("PUMP 2(hide)"); Button boilb1=new Button("BOILER 2(hide)");
Button resett1 = new Button("RESET");           Button resetp1 = new Button("RESET");         Button resetb1 = new Button("RESET");
Button turbineb2 = new Button("TURBINE 3(hide)"); Button pumpb2 = new Button("PUMP 3(hide)"); Button condb0=new Button("CONDENSER 1(hide)");
Button resett2 = new Button("RESET");           Button resetp2 = new Button("RESET");         Button resetc0 = new Button("RESET");
Button turbineb3 = new Button("TURBINE 4(hide)"); Button pumpb3 = new Button("PUMP 4(hide)"); Button condb1=new Button("CONDENSER 2(hide)");
Button resett3 = new Button("RESET");           Button resetp3 = new Button("RESET");         Button resetc1 = new Button("RESET");


TextField[] f = new TextField[139];
boolean[] textval =new boolean[139];
int[] calindex=new int[139];
private String dir = "C:/Users/Admin/Desktop/cycle pics/cycle1.jpg";   
double[] texts=new double[139];
 public void init() 
 {
 for(int i=0;i<139;i++)
 {
     f[i]=new TextField(10);
      if(i==31||i==38||i==45||i==52||i==83||i==90||i==97||i==104)
   {
       f[i].setText("1");
    }
    }
   try {
            URL pic = new URL(getDocumentBase(), "cycle1.png");
            aang1 = ImageIO.read(pic);
            URL pic1 = new URL(getDocumentBase(), "cycle2.png");
            aang2 = ImageIO.read(pic1);
            URL pic2 = new URL(getDocumentBase(), "cycle3.png");
            aang3 = ImageIO.read(pic2);
            URL pic3 = new URL(getDocumentBase(), "cycle4.png");
            aang4 = ImageIO.read(pic3);
            URL pic4 = new URL(getDocumentBase(), "cycle5.png");
            aang5 = ImageIO.read(pic4);
            URL pic5 = new URL(getDocumentBase(), "cycle6.png");
            aang6 = ImageIO.read(pic5);
        } catch(Exception e) {
            // tell us if anything goes wrong!
            e.printStackTrace();
        } 
 for(int i=0;i<24;i++)
{
states[i][0]=new Label("TEMPERATURE");
states[i][1]=new Label("PRESSURE");
states[i][2]=new Label("ENTHALPY");
states[i][3]=new Label("ENTROPY");
if((i>=0&&i<=3)||(i>=8&&i<=11))
{
states[i][4]=new Label("QUALITY");
states[i][5]=new Label("MASS FLOW RATE");
states[i][6]=new Label("");
}
else if((i>=4&&i<=7)||(i>=12&&i<=15))
{
states[i][4]=new Label("QUALITY");
states[i][5]=new Label("MASS FLOW RATE");
states[i][6]=new Label("EFFICIENCY");
}
else if((i>=16&&i<=19)||(i>=22&&i<=23))
{
states[i][4]=new Label("");
states[i][5]=new Label("");
states[i][6]=new Label("");
}
else if(i>=20&&i<=21)
{
states[i][4]=new Label("QUALITY");
states[i][5]=new Label("");
states[i][6]=new Label("");
}
}

setLayout(new BorderLayout());
setBackground(Color.GREEN);
p1.setLayout(new GridLayout(0,6));
p1.setBackground(Color.ORANGE);

p2.setLayout(new GridLayout(0,1));
p2.setBackground(Color.ORANGE);
//panel initializations for turbine inputs
pli0.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<6;i++)
{
pli0.add(states[0][i]);
}
pli1.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<6;i++)
{
pli1.add(states[1][i]);
}

pli2.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<6;i++)
{
pli2.add(states[2][i]);
}
pli3.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<6;i++)
{
pli3.add(states[3][i]);
}
//panel initialization for pump inputs
pli4.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<6;i++)
{
pli4.add(states[8][i]);
}
pli5.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<6;i++)
{
pli5.add(states[9][i]);
}
pli6.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<6;i++)
{
pli6.add(states[10][i]);
}
pli7.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<6;i++)
{
pli7.add(states[11][i]);
}
//panel initialization for turbine outputs
plo0.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<7;i++)
{
plo0.add(states[4][i]);
}
plo1.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<7;i++)
{
plo1.add(states[5][i]);
}
plo2.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<7;i++)
{
plo2.add(states[6][i]);
}
plo3.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<7;i++)
{
plo3.add(states[7][i]);
}
//panel initialization for pump outputs
plo4.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<7;i++)
{
plo4.add(states[12][i]);
}
plo5.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<7;i++)
{
plo5.add(states[13][i]);
}
plo6.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<7;i++)
{
plo6.add(states[14][i]);
}
plo7.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<7;i++)
{
plo7.add(states[15][i]);
}
//panel initialization for boiler and condenser inputs
//boiler
pli8.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<4;i++)
{
pli8.add(states[16][i]);
}
pli9.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<4;i++)
{
pli9.add(states[17][i]);
}
//condenser
pli10.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<5;i++)
{
pli10.add(states[20][i]);
}
pli11.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<5;i++)
{
pli11.add(states[21][i]);
}
//panel initialization for boiler and condenser outputs
//boiler
plo8.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<4;i++)
{
plo8.add(states[18][i]);
}
plo9.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<4;i++)
{
plo9.add(states[19][i]);
}
//condenser
plo10.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<4;i++)
{
plo10.add(states[22][i]);
}
plo11.setLayout(new GridLayout(0,1,5,0));
for(int i=0;i<4;i++)
{
plo11.add(states[23][i]);
}
start1.setLayout(new GridLayout(0,1,2,0));
start2.setLayout(new GridLayout(0,1,3,0));

pi0.setLayout(new GridLayout(0,1,6,0));
pi0.setBackground(Color.CYAN);

po0.setLayout(new GridLayout(0,1,7,0));
po0.setBackground(Color.CYAN);

pi1.setLayout(new GridLayout(0,1,6,0));
pi1.setBackground(Color.CYAN);

po1.setLayout(new GridLayout(0,1,7,0));
po1.setBackground(Color.CYAN);

pi2.setLayout(new GridLayout(0,1,6,0));
pi2.setBackground(Color.CYAN);

po2.setLayout(new GridLayout(0,1,7,0));
po2.setBackground(Color.CYAN);

pi3.setLayout(new GridLayout(0,1,6,0));
pi3.setBackground(Color.CYAN);

po3.setLayout(new GridLayout(0,1,7,0));
po3.setBackground(Color.CYAN);

pi4.setLayout(new GridLayout(0,1,6,0));
pi4.setBackground(Color.CYAN);

po4.setLayout(new GridLayout(0,1,7,0));
po4.setBackground(Color.CYAN);

pi5.setLayout(new GridLayout(0,1,6,0));
pi5.setBackground(Color.CYAN);

po5.setLayout(new GridLayout(0,1,7,0));
po5.setBackground(Color.CYAN);

pi6.setLayout(new GridLayout(0,1,6,0));
pi6.setBackground(Color.CYAN);

po6.setLayout(new GridLayout(0,1,7,0));
po6.setBackground(Color.CYAN);

pi7.setLayout(new GridLayout(0,1,6,0));
pi7.setBackground(Color.CYAN);

po7.setLayout(new GridLayout(0,1,7,0));
po7.setBackground(Color.CYAN);

pi8.setLayout(new GridLayout(0,1,6,0));
pi8.setBackground(Color.CYAN);

po8.setLayout(new GridLayout(0,1,7,0));
po8.setBackground(Color.CYAN);

pi9.setLayout(new GridLayout(0,1,6,0));
pi9.setBackground(Color.CYAN);

po9.setLayout(new GridLayout(0,1,7,0));
po9.setBackground(Color.CYAN);

pi10.setLayout(new GridLayout(0,1,6,0));
pi10.setBackground(Color.CYAN);

po10.setLayout(new GridLayout(0,1,7,0));
po10.setBackground(Color.CYAN);

pi11.setLayout(new GridLayout(0,1,6,0));
pi11.setBackground(Color.CYAN);

po11.setLayout(new GridLayout(0,1,7,0));
po11.setBackground(Color.CYAN);

  process.add("NEW TURBINE");
  process.add("NEW FEED PUMP");
  process.add("NEW CONDENSER");
  process.add("NEW BOILER");
  work_fl.add("MERCURY");
  
  work_fl.add("STEAM");
  work_fl.add("OTHER(input all manually)");
  
  prob_type.add("TYPE 1");
  prob_type.add("TYPE 2");
  prob_type.add("TYPE 3");
  prob_type.add("TYPE 4");
  prob_type.add("TYPE 5");
  prob_type.add("TYPE 6");
   
   process.addItemListener(this); 
   work_fl.addItemListener(this);
   prob_type.addItemListener(this);
   //adding the test boxes to the panels
  
   pi0.add(f[1]);pi1.add(f[7]);pi2.add(f[13]);pi3.add(f[19]);
   pi0.add(f[2]);pi1.add(f[8]);pi2.add(f[14]);pi3.add(f[20]);
   pi0.add(f[3]);pi1.add(f[9]);pi2.add(f[15]);pi3.add(f[21]);
   pi0.add(f[4]);pi1.add(f[10]);pi2.add(f[16]);pi3.add(f[22]);
   pi0.add(f[5]);pi1.add(f[11]);pi2.add(f[17]);pi3.add(f[23]);
   pi0.add(f[6]);pi1.add(f[12]);pi2.add(f[18]);pi3.add(f[24]);
   
   po0.add(f[25]);po1.add(f[32]);po2.add(f[39]);po3.add(f[46]);
   po0.add(f[26]);po1.add(f[33]);po2.add(f[40]);po3.add(f[47]);
   po0.add(f[27]);po1.add(f[34]);po2.add(f[41]);po3.add(f[48]);
   po0.add(f[28]);po1.add(f[35]);po2.add(f[42]);po3.add(f[49]);
   po0.add(f[29]);po1.add(f[36]);po2.add(f[43]);po3.add(f[50]);
   po0.add(f[30]);po1.add(f[37]);po2.add(f[44]);po3.add(f[51]);
   po0.add(f[31]);po1.add(f[38]);po2.add(f[45]);po3.add(f[52]);
  
   pi4.add(f[53]);pi5.add(f[59]);pi6.add(f[65]);pi7.add(f[71]);
   pi4.add(f[54]);pi5.add(f[60]);pi6.add(f[66]);pi7.add(f[72]);
   pi4.add(f[55]);pi5.add(f[61]);pi6.add(f[67]);pi7.add(f[73]);
   pi4.add(f[56]);pi5.add(f[62]);pi6.add(f[68]);pi7.add(f[74]);
   pi4.add(f[57]);pi5.add(f[63]);pi6.add(f[69]);pi7.add(f[75]);
   pi4.add(f[58]);pi5.add(f[64]);pi6.add(f[70]);pi7.add(f[76]);
   
   po4.add(f[77]);po5.add(f[84]);po6.add(f[91]);po7.add(f[98]);
   po4.add(f[78]);po5.add(f[85]);po6.add(f[92]);po7.add(f[99]);
   po4.add(f[79]);po5.add(f[86]);po6.add(f[93]);po7.add(f[100]);
   po4.add(f[80]);po5.add(f[87]);po6.add(f[94]);po7.add(f[101]);
   po4.add(f[81]);po5.add(f[88]);po6.add(f[95]);po7.add(f[102]);
   po4.add(f[82]);po5.add(f[89]);po6.add(f[96]);po7.add(f[103]);
   po4.add(f[83]);po5.add(f[90]);po6.add(f[97]);po7.add(f[104]);
   
   pi8.add(f[105]);pi9.add(f[109]);pi10.add(f[113]);pi11.add(f[118]);
   pi8.add(f[106]);pi9.add(f[110]);pi10.add(f[114]);pi11.add(f[119]);
   pi8.add(f[107]);pi9.add(f[111]);pi10.add(f[115]);pi11.add(f[120]);
   pi8.add(f[108]);pi9.add(f[112]);pi10.add(f[116]);pi11.add(f[121]);
                               pi10.add(f[117]);pi11.add(f[122]);
   
   
   po8.add(f[123]);po9.add(f[127]);po10.add(f[131]);po11.add(f[135]);
   po8.add(f[124]);po9.add(f[128]);po10.add(f[132]);po11.add(f[136]);
   po8.add(f[125]);po9.add(f[129]);po10.add(f[133]);po11.add(f[137]);
   po8.add(f[126]);po9.add(f[130]);po10.add(f[134]);po11.add(f[138]);
   int i=0;
  
//Then add the drop down list to the screen
  start1.add(process);
  start1.add(work_fl);
  start1.add(prob_type);
  
  start2.add(create);
  start2.add(calculate);
  start2.add(diagram);
  
  p1.add(start1);
  p1.add(lb1);
  p1.add(lb3);
  p1.add(lb2);
  p1.add(lb4);
  p1.add(start2);
 create.addActionListener(this);
 calculate.addActionListener(this);
 diagram.addActionListener(this);
 
 turbineb0.addActionListener(this); pumpb0.addActionListener(this); boilb0.addActionListener(this);
 turbineb1.addActionListener(this); pumpb1.addActionListener(this); boilb1.addActionListener(this);
 turbineb2.addActionListener(this); pumpb2.addActionListener(this); condb0.addActionListener(this);
 turbineb3.addActionListener(this); pumpb3.addActionListener(this); condb1.addActionListener(this);
 
 resett0.addActionListener(this); resetp0.addActionListener(this); resetb0.addActionListener(this); 
 resett1.addActionListener(this); resetp1.addActionListener(this); resetb1.addActionListener(this);
 resett2.addActionListener(this); resetp2.addActionListener(this); resetc0.addActionListener(this); 
 resett3.addActionListener(this); resetp3.addActionListener(this); resetc1.addActionListener(this);
 
 add(p1,BorderLayout.NORTH); 
 add(p2,BorderLayout.SOUTH); 
 }
  public void paint (Graphics g)
   {
       g.setFont(new Font("default", Font.BOLD, 14));
      g.drawString ("Hello user, chose your process and working fluid from the dropdown list above and click create to add the process ", 5, 100);
      g.drawString ("Type 1----Saturated wfl involving one set of turbine inlet and/or condenser inlet values,input turbine inlet pressure", 5, 120);
       g.drawString ("Type 2----Saturated wfl involving one set of turbine inlet and/or condenser inlet values,input turbine inlet temperature", 5, 140);
        g.drawString ("Type 3----Problems in superheated wfl involving one set of turbine inlet and/or condenser inlet values", 5, 160);
         g.drawString ("Type 4----Problems involving reheat cycle", 5, 180);
          g.drawString ("Type 5----Problems involving ideal openfeed water heater regenerative cycle", 5, 200);
           g.drawString ("Type 6----Problems involving binary cycle of mercury and steam", 5, 220);
           g.drawString ("Please type all pressure values in bar,temperature in celcius and other values in their S.I units", 5, 250);
           g.drawString ("Answers are for all pressure values in bar,temperature in celcius and other values in their S.I units", 5, 280);
      if(diag>0)
      {
      if (probno==1&&aang1!=null) {
            g.drawImage(aang1, 0, 75, this);
        }
        if (probno==2&&aang2!=null) {
            g.drawImage(aang2, 0, 75, this);
        }
        if (probno==3&&aang3!=null) {
            g.drawImage(aang3, 0, 75, this);
        }
        if (probno==4&&aang4!=null) {
            g.drawImage(aang4, 0, 75, this);
        }
        if (probno==5&&aang5!=null) {
            g.drawImage(aang5, 0, 75, this);
        }
        if (probno==6&&aang6!=null) {
            g.drawImage(aang6, 0, 75, this);
        }
       
    }
        
      
    }
   public void actionPerformed(ActionEvent event)
{
Object source = event.getSource();
if (source==create)
{
if(tcount[0]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
twf[0]=1;
else if(work_fl.getSelectedItem()=="STEAM")
twf[0]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
twf[0]=2;
}
else if(tcount[1]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
twf[1]=1;
else if(work_fl.getSelectedItem()=="STEAM")
twf[1]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
twf[1]=2;
}
else if(tcount[2]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
twf[2]=1;
else if(work_fl.getSelectedItem()=="STEAM")
twf[2]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
twf[2]=2;
}
else if(tcount[3]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
twf[3]=1;
else if(work_fl.getSelectedItem()=="STEAM")
twf[3]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
twf[3]=2;
}
if(bcount[0]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
bwf[0]=1;
else if(work_fl.getSelectedItem()=="STEAM")
bwf[0]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
bwf[0]=2;
}
else if(bcount[1]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
bwf[1]=1;
else if(work_fl.getSelectedItem()=="STEAM")
bwf[1]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
bwf[1]=2;
}
if(ccount[0]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
cwf[0]=1;
else if(work_fl.getSelectedItem()=="STEAM")
cwf[0]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
cwf[0]=2;
}
else if(ccount[1]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
cwf[1]=1;
else if(work_fl.getSelectedItem()=="STEAM")
cwf[1]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
cwf[1]=2;
}
if(pcount[0]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
pwf[0]=1;
else if(work_fl.getSelectedItem()=="STEAM")
pwf[0]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
pwf[0]=2;
}
if(pcount[1]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
pwf[1]=1;
else if(work_fl.getSelectedItem()=="STEAM")
pwf[1]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
pwf[1]=2;
}
if(pcount[2]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
pwf[2]=1;
else if(work_fl.getSelectedItem()=="STEAM")
pwf[2]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
pwf[2]=2;
}
if(pcount[3]==0)
{
if(work_fl.getSelectedItem()=="MERCURY")
pwf[3]=1;
else if(work_fl.getSelectedItem()=="STEAM")
pwf[3]=0;
else if(work_fl.getSelectedItem()=="OTHER(input all manually)")
pwf[3]=2;
}
}
if (source == create)
{
if(turb==true)
{

if(tcount[0]==0)
{
p1.add(turbineb0);
 
p1.add(pli0);
p1.add(pi0);
p1.add(plo0);
p1.add(po0);
p1.add(resett0);
 tcount[0]=1;

 invalidate();
 validate();
}
else if(tcount[1]==0)
{
p1.add(turbineb1);
 
p1.add(pli1);
p1.add(pi1);
p1.add(plo1);
p1.add(po1);
p1.add(resett1);
 tcount[1]=1;

 invalidate();
 validate();
}
else if(tcount[2]==0)
{
p1.add(turbineb2);
 
p1.add(pli2);
p1.add(pi2);
p1.add(plo2);
p1.add(po2);
p1.add(resett2);
 tcount[2]=1;

 invalidate();
 validate();
}
else if(tcount[3]==0)
{
p1.add(turbineb3);
 
p1.add(pli3);
p1.add(pi3);
p1.add(plo3);
p1.add(po3);
p1.add(resett3);
 tcount[3]=1;

 invalidate();
 validate();
}
if(tc<3)tc++;
//if (tc>2)tc=0;
}
if(boil==true)
{
if(bcount[0]==0)
{
p1.add(boilb0);
 
p1.add(pli8);
p1.add(pi8);
p1.add(plo8);
p1.add(po8);
p1.add(resetb0);
 bcount[0]=1;

 invalidate();
 validate();
}
else if(bcount[1]==0)
{
p1.add(boilb1);
 
p1.add(pli9);
p1.add(pi9);
p1.add(plo9);
p1.add(po9);
p1.add(resetb1);
 bcount[1]=1;

 invalidate();
 validate();
}

if(bc<3)bc++;
//if (tc>2)tc=0;
}
if(cond==true)
{
if(ccount[0]==0)
{
p1.add(condb0);
 
p1.add(pli10);
p1.add(pi10);
p1.add(plo10);
p1.add(po10);
p1.add(resetc0);
 ccount[0]=1;

 invalidate();
 validate();
}
else if(ccount[1]==0)
{
p1.add(condb1);
 
p1.add(pli11);
p1.add(pi11);
p1.add(plo11);
p1.add(po11);
p1.add(resetc1);
 ccount[1]=1;

 invalidate();
 validate();
}

if(cc<3)cc++;
//if (tc>2)tc=0;
}
if(pump==true)
{
if(pcount[0]==0)
{
p1.add(pumpb0);
 
p1.add(pli4);
p1.add(pi4);
p1.add(plo4);
p1.add(po4);
p1.add(resetp0);
 pcount[0]=1;

 invalidate();
 validate();
}
else if(pcount[1]==0)
{
p1.add(pumpb1);
 
p1.add(pli5);
p1.add(pi5);
p1.add(plo5);
p1.add(po5);
p1.add(resetp1);
 pcount[1]=1;

 invalidate();
 validate();
}
else if(pcount[2]==0)
{

p1.add(pumpb2);
 
p1.add(pli6);
p1.add(pi6);
p1.add(plo6);
p1.add(po6);
p1.add(resetp2);
 pcount[2]=1;

 invalidate();
 validate();
}
else if(pcount[3]==0)
{
p1.add(pumpb3);
 
p1.add(pli7);
p1.add(pi7);
p1.add(plo7);
p1.add(po7);
p1.add(resetp3);
 pcount[3]=1;

 invalidate();
 validate();
}
if(pc<3)tc++;
//if (tc>2)tc=0;
}
}
if (source == turbineb0)
{
tc=0;
tcount[tc]=0;
p1.remove(turbineb0);
 
p1.remove(pli0);
p1.remove(pi0);
p1.remove(plo0);
p1.remove(po0);
p1.remove(resett0);
 
 invalidate();
 validate();
}
if (source == turbineb1)
{
tc=1;
tcount[tc]=0;
p1.remove(turbineb1);
 
 
p1.remove(pli1);
p1.remove(pi1);
p1.remove(plo1);
p1.remove(po1);
p1.remove(resett1);
 
 invalidate();
 validate();
}
if (source == turbineb2)
{
tc=2;
tcount[tc]=0;
p1.remove(turbineb2);
 
 
p1.remove(pli2);
p1.remove(pi2);
p1.remove(plo2);
p1.remove(po2);
p1.remove(resett2);
 
 invalidate();
 validate();
}
if (source == turbineb3)
{
tc=3;
tcount[tc]=0;
p1.remove(turbineb3);
 
 
p1.remove(pli3);
p1.remove(pi3);
p1.remove(plo3);
p1.remove(po3);
p1.remove(resett3);
 
 invalidate();
 validate();
}
if (source == boilb0)
{
bc=0;
bcount[bc]=0;
p1.remove(boilb0);
 
p1.remove(pli8);
p1.remove(pi8);
p1.remove(plo8);
p1.remove(po8);
p1.remove(resetb0);
 
 invalidate();
 validate();
}
if (source == boilb1)
{
bc=1;
bcount[bc]=0;
p1.remove(boilb1);
 
 
p1.remove(pli9);
p1.remove(pi9);
p1.remove(plo9);
p1.remove(po9);
p1.remove(resetb1);
 
 invalidate();
 validate();
}
if (source == condb0)
{
cc=0;
ccount[cc]=0;
p1.remove(condb0);
 
 
p1.remove(pli10);
p1.remove(pi10);
p1.remove(plo10);
p1.remove(po10);
p1.remove(resetc0);
 
 invalidate();
 validate();
}
if (source == condb1)
{
cc=1;
ccount[cc]=0;
p1.remove(condb1);
p1.remove(pli11);
p1.remove(pi11);
p1.remove(plo11);
p1.remove(po11);
p1.remove(resetc1);
 
 invalidate();
 validate();
}
if (source == resett0)
{twf[0]=-1;
for(int i=1;i<=6;i++)
{
f[i].setText("");
}

for(int i=25;i<=31;i++)
{
f[i].setText("");
}f[31].setText("1");
 invalidate();
 validate();
}
if (source == resett1)
{twf[1]=-1;
for(int i=7;i<=12;i++)
{
f[i].setText("");
}
for(int i=32;i<=38;i++)
{
f[i].setText("");
}f[38].setText("1");
 invalidate();
 validate();
}
if (source == resett2)
{twf[2]=-1;
for(int i=13;i<=18;i++)
{
f[i].setText("");
}
for(int i=39;i<=45;i++)
{
f[i].setText("");
}f[45].setText("1");

 invalidate();
 validate();
}
if (source == resett3)
{twf[3]=-1;
for(int i=19;i<=24;i++)
{
f[i].setText("");
}
for(int i=46;i<=52;i++)
{
f[i].setText("");
}f[52].setText("1");
 invalidate();
 validate();
}
if (source == resetb0)
{bwf[0]=-1;
for(int i=105;i<=108;i++)
{
f[i].setText("");
}
for(int i=123;i<=126;i++)
{
f[i].setText("");
}
 invalidate();
 validate();
}
if (source == resetb1)
{bwf[1]=-1;
for(int i=109;i<=112;i++)
{
f[i].setText("");
}
for(int i=127;i<=130;i++)
{
f[i].setText("");
}

 invalidate();
 validate();
}
if (source == resetc0)
{cwf[0]=-1;
for(int i=113;i<=117;i++)
{
f[i].setText("");
}
for(int i=131;i<=134;i++)
{
f[i].setText("");
}

 invalidate();
 validate();
}
if (source == resetc1)
{cwf[1]=-1;
for(int i=118;i<=122;i++)
{
f[i].setText("");
}
for(int i=135;i<=138;i++)
{
f[i].setText("");
}

 invalidate();
 validate();
}
if (source == pumpb0)
{
pc=0;
pcount[pc]=0;
p1.remove(pumpb0);
 
p1.remove(pli4);
p1.remove(pi4);
p1.remove(plo4);
p1.remove(po4);
p1.remove(resetp0);
 
 invalidate();
 validate();
}
if (source == pumpb1)
{
pc=1;
pcount[pc]=0;
p1.remove(pumpb1);
p1.remove(pli5);
p1.remove(pi5);
p1.remove(plo5);
p1.remove(po5);
p1.remove(resetp1);
 
 invalidate();
 validate();
}
if (source == pumpb2)
{
pc=2;
pcount[pc]=0;
p1.remove(pumpb2);
p1.remove(pli6);
p1.remove(pi6);
p1.remove(plo6);
p1.remove(po6);
p1.remove(resetp2);
 
 invalidate();
 validate();
}
if (source == pumpb3)
{
pc=3;
pcount[pc]=0;
p1.remove(pumpb3);
p1.remove(pli7);
p1.remove(pi7);
p1.remove(plo7);
p1.remove(po7);
p1.remove(resetp3);
 
 invalidate();
 validate();
}
if (source == resetp0)
{pwf[0]=-1;
for(int i=53;i<=58;i++)
{
f[i].setText("");
}
for(int i=77;i<=83;i++)
{
f[i].setText("");
}f[83].setText("1");

 invalidate();
 validate();
}
if (source == resetp1)
{pwf[1]=-1;
for(int i=59;i<=64;i++)
{
f[i].setText("");
}
for(int i=84;i<=90;i++)
{
f[i].setText("");
}f[90].setText("1");

 invalidate();
 validate();
}
if (source == resetp2)
{pwf[2]=-1;
for(int i=65;i<=70;i++)
{
f[i].setText("");
}
for(int i=91;i<=97;i++)
{
f[i].setText("");
}f[97].setText("1");

 invalidate();
 validate();
}
if (source == resetp3)
{pwf[3]=-1;
for(int i=71;i<=76;i++)
{
f[i].setText("");
}
for(int i=98;i<=104;i++)
{
f[i].setText("");
}f[104].setText("1");

 invalidate();
 validate();
}
if(source ==calculate)
{
 
if(probno!=0)
{
findresults(probno);



// String cons=new String("The Rankine cylce efficiency is :"+rankineeff+" \nThe Carnot efficiency is :"+carnoteff+"\nCarnot Work ratio: "+workratio+"\n");
// 
// String outp=cons.concat(result);
JOptionPane.showMessageDialog(null,result,"RESULTS",
JOptionPane.PLAIN_MESSAGE); setVisible(true); // show something
invalidate();
validate();
repaint();
}
else{
JOptionPane.showMessageDialog(null,"SORRY ,YOU ARE MAKING A MISTAKE SOMEWHERE","RESULTS",
JOptionPane.PLAIN_MESSAGE); setVisible(true); // show something
invalidate();
validate();
repaint();
}
}


if(source == diagram)
{
diag=diag*-1;
repaint();
// // JOptionPane.showMessageDialog(null,"SORRY[] ,YOU ARE MAKING A MISTAKE SOMEWHERE","RESULTS",
// // JOptionPane.PLAIN_MESSAGE); setVisible(true); // show something
// Panel p2=new Panel();
// Icon icon = new ImageIcon("C:\\Users\\ADMIN\\Desktop\\cycle pics\\cycle1.jpg");
//        // Image image = ImageIO.read(new File("C:\\Users\\ADMIN\\Desktop\\cycle pics\\cycle1.jpg"));
// JLabel thumb = new JLabel();
// thumb.setIcon(icon);
// p2.add(thumb);
// //JOptionPane.showMessageDialog(null, thumb, "About", JOptionPane.INFORMATION_MESSAGE);
//        
// invalidate();
// validate();
// //repaint();
}
}
 public  void findresults(int probtypeno)
 {int cot=0;int cop=0;int cob=0;int coc=0;
   for(int i=0;i<4;i++)
   {
       if(twf[i]!=-1)
       cot++;
       if(pwf[i]!=-1)
       cop++;
       if(i<2)
       {
           if(cwf[i]!=-1)
           coc++;
           if(bwf[i]!=-1)
           cob++;
        }
    }
    
        int pt=-1;int pc=-1;int pp=1;int pt1=-1;int ptm=-1;int pcm=-1;//get the condenser and turbine number
         for(int i=0;i<4;i++)
   {
       if(twf[i]!=-1)
       {
      if(pt==-1){pt=i;}
      else if(pt1==-1&&pt!=i){
          pt1=i;
          if(twf[i]==1)
          {
              ptm=pt1;
            }
        }
    }
       if(pwf[i]!=-1)
       pp=i;
       if(i<2)
       {
           if(cwf[i]!=-1)
           {
           pc=i;
           if(cwf[i]==1)pcm=pc;
        }
        }
    }
    
         double a=0;double b=0;double tem=0;double a1=0;double tem1=0;int j=0;
         int z1=(pt*6)+2;int z2=(pt*7)+26;int z3=(pc*5)+114;boolean found=false;
         if(probtypeno==1||probtypeno==2)
         {
             
            if(probtypeno==1)
            {
              
              a=Double.valueOf(f[(pt*6)+2].getText());
              b=Double.valueOf(f[114+(pc*5)].getText());
            }
            if(probtypeno==2)
            {
            tem=Double.valueOf(f[(pt*6)+1].getText());
        
             while(found==false)
             {
                 double v=steam_sat_t[j];
                 if(v<tem)
                 {
                 j+=10;
                }
                if(v>=tem)
                {
                    found=true;
                    if(v==tem)
                    {a=steam_sat_t[j+1];}
                    else 
                    {
                        a=steam_sat_t[j-9]+((steam_sat_t[j+1]-steam_sat_t[j-9])/(steam_sat_t[j]-steam_sat_t[j-10]))*(tem-steam_sat_t[j-10]);
                    }
                }
            }
             b=Double.valueOf(f[114+(pc*5)].getText());
            }
        
             double tureff=Double.valueOf(f[24+(pt*7)+7].getText());  double pumeff=Double.valueOf(f[76+(pp*7)+7].getText());
             double sgh=0;double sfh=0;double h2=0;double sfg=0;double sfl=0;double hfl=0;double hfg=0;double h3s=0;double h3=0;double hc=0;double h1=0;
             double vfl=0;double h4s=0;double h4=0;double hc4=0;double it=0;double ci=0;found=false;j=0;
             while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<a)
                 {
                 j+=10;
                }
                if(v>=a)
                {
                    found=true;
                    if(v==a)
                    {sgh=steam_sat_p[j+9];h2=steam_sat_p[j+7];sfh=steam_sat_p[j+8];h1=steam_sat_p[j+6];it=steam_sat_p[j+1];}
                    else 
                    {
                        sgh=steam_sat_p[j-1]+((steam_sat_p[j+9]-steam_sat_p[j-1])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a-steam_sat_p[j-10]);
                        sfh=steam_sat_p[j-2]+((steam_sat_p[j+8]-steam_sat_p[j-2])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a-steam_sat_p[j-10]);
                       
                        h2=steam_sat_p[j-3]+((steam_sat_p[j+7]-steam_sat_p[j-3])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a-steam_sat_p[j-10]);
                        h1=steam_sat_p[j-4]+((steam_sat_p[j+6]-steam_sat_p[j-4])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a-steam_sat_p[j-10]);
                        it=steam_sat_p[j-9]+((steam_sat_p[j+1]-steam_sat_p[j-9])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a-steam_sat_p[j-10]);
                    }
                }
            }
            found=false;j=0;
             while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<b)
                 {
                 j+=10;
                }
                if(v>=b)
                {
                    found=true;
                    if(v==b){sfg=(steam_sat_p[j+9]-steam_sat_p[j+8]);sfl=steam_sat_p[j+8];vfl=steam_sat_p[j+2]/1000;hfg=steam_sat_p[j+7]-steam_sat_p[j+6];
                    hfl=steam_sat_p[j+6];ci=steam_sat_p[j+1];}
                    else
                    {
                       double sfg1=steam_sat_p[j-1]+((steam_sat_p[j+9]-steam_sat_p[j-1])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                       
                         double sfg2=steam_sat_p[j-2]+((steam_sat_p[j+8]-steam_sat_p[j-2])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         double hfg1=steam_sat_p[j-3]+((steam_sat_p[j+7]-steam_sat_p[j-3])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         
                         double hfg2=steam_sat_p[j-4]+((steam_sat_p[j+6]-steam_sat_p[j-4])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=steam_sat_p[j-8]+((steam_sat_p[j+2]-steam_sat_p[j-8])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         ci=steam_sat_p[j-9]+((steam_sat_p[j+1]-steam_sat_p[j-9])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=vfl/1000;
                         hfg=(hfg1-hfg2);
                         hfl=hfg2;
                         sfg=(sfg1-sfg2);
                         sfl=sfg2;
                    }
                }
            }
            double x=(sgh-sfl)/sfg;
            h3s=hfl+x*hfg;   
            double x1=(sfh-sfl)/sfg;
            hc4=hfl+x1*hfg;
            carnoteff=((h2-h3s)-(h1-hc4))/(h2-h1);
            carnoteff=carnoteff*100;
            h4s=vfl*(a-b)*100+hfl;   h4=((h4s-hfl)/pumeff)+hfl;h3=h2-(h2-h3s)*tureff;
            rankineeff=((h2-h3)-(h4-hfl))/(h2-h4);
            rankineeff=rankineeff*100;
            workratio=((h2-h3)-(h1-hc4))/(h2-h3);
           double scc=1/((h2-h3)-(h4-hfl));scc=scc*1000;
           String cons=new String("The Rankine cylce efficiency is :"+rankineeff+" \nThe Carnot efficiency is :"+carnoteff+"\nCarnot Work ratio: "+workratio+"\n");
            
            String outp="turbine inlet temperature "+it+"\n"+"turbine inlet enthalpy "+h2+"\n"+"condenser inlet temperature "+ci+"\n"+"pump work "+(h4-hfl)+"\n"+"Specific steam consumption "+scc+"\n";
            result=cons.concat(outp);
        }
        else if(probtypeno==3)
        {
            double tureff=Double.valueOf(f[24+(pt*7)+7].getText());  double pumeff=Double.valueOf(f[76+(pp*7)+7].getText());
            double sgh=0;double sfh=0;double h2=0;double sfg=0;double sfl=0;double hfl=0;double hfg=0;double h3s=0;double h3=0;double hc=0;double h1=0;
            double vfl=0;double h4s=0;double h4=0;double hc4=0;double it=0;double ci=0;found=false;j=0;
             
            j=0;a=Double.valueOf(f[(pt*6)+2].getText());b=Double.valueOf(f[114+(pc*5)].getText());tem=Double.valueOf(f[(pt*6)+1].getText());
            found=false;
            int z=0;int zz=0;
                while(found==false)
                {
                    if(supersteam[j]==a)
                    {
                        found=true;z=j;
                    }
                    else if(j==supersteam.length-1)
                    {
                     found=true;z=j;
                    }
                    j++;
                }
                if(j!=supersteam.length-1)
                {j=0;
                 found=false;
                 while(found==false)
                 {
                     if(supersteam[z+j]==tem){found=true;zz=z+j;}
                     else if(supersteam[z+j-1]==1000){found=true;zz=z+j;}
                     j++;
                     
                    }
                }
                h2=supersteam[zz+3];sgh=supersteam[zz+4];found=false;j=0;
                while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<a)
                 {
                 j+=10;
                }
                if(v>=a)
                {
                    found=true;
                    if(v==a)
                    {sfh=steam_sat_p[j+8];h1=steam_sat_p[j+6];}
                    else 
                    {
                       
                        sfh=steam_sat_p[j-2]+((steam_sat_p[j+8]-steam_sat_p[j-2])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a-steam_sat_p[j-10]);
                       
                        
                        h1=steam_sat_p[j-4]+((steam_sat_p[j+6]-steam_sat_p[j-4])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a-steam_sat_p[j-10]);
                        
                    }
                }
            }
            found=false;j=0;
             while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<b)
                 {
                 j+=10;
                }
                if(v>=b)
                {
                    found=true;
                    if(v==b){sfg=(steam_sat_p[j+9]-steam_sat_p[j+8]);sfl=steam_sat_p[j+8];vfl=steam_sat_p[j+2]/1000;hfg=steam_sat_p[j+7]-steam_sat_p[j+6];
                    hfl=steam_sat_p[j+6];ci=steam_sat_p[j+1];}
                    else
                    {
                       double sfg1=steam_sat_p[j-1]+((steam_sat_p[j+9]-steam_sat_p[j-1])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                       
                         double sfg2=steam_sat_p[j-2]+((steam_sat_p[j+8]-steam_sat_p[j-2])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         double hfg1=steam_sat_p[j-3]+((steam_sat_p[j+7]-steam_sat_p[j-3])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         
                         double hfg2=steam_sat_p[j-4]+((steam_sat_p[j+6]-steam_sat_p[j-4])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=steam_sat_p[j-8]+((steam_sat_p[j+2]-steam_sat_p[j-8])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         ci=steam_sat_p[j-9]+((steam_sat_p[j+1]-steam_sat_p[j-9])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=vfl/1000;
                         hfg=(hfg1-hfg2);
                         hfl=hfg2;
                         sfg=(sfg1-sfg2);
                         sfl=sfg2;
                    }
                }
            }
            double x=(sgh-sfl)/sfg;
            h3s=hfl+x*hfg;   
            double x1=(sfh-sfl)/sfg;
            hc4=hfl+x1*hfg;
            carnoteff=((h2-h3s)-(h1-hc4))/(h2-h1);
            carnoteff=carnoteff*100;
            h4s=vfl*(a-b)*100+hfl;   h4=((h4s-hfl)/pumeff)+hfl;h3=h2-(h2-h3s)*tureff;
            rankineeff=((h2-h3)-(h4-hfl))/(h2-h4);
            rankineeff=rankineeff*100;
            workratio=((h2-h3)-(h1-hc4))/(h2-h3);
           double scc=1/((h2-h3)-(h4-hfl));scc=scc*1000;
           String cons=new String("The Rankine cylce efficiency is :"+rankineeff+" \nThe Carnot efficiency is :"+carnoteff+"\nCarnot Work ratio: "+workratio+"\n");
           
           String outp="turbine inlet enthalpy "+h2+"\n"+"condenser inlet temperature "+ci+"\n"+"pump work "+(h4-hfl)+"\n"+"Specific steam consumption "+scc+"\n";
           result=cons.concat(outp); 
           j=0;
        }
        else if(probtypeno==4)
        {
           double tureff=Double.valueOf(f[24+(pt*7)+7].getText());  double pumeff=Double.valueOf(f[76+(pp*7)+7].getText());double tureff1=Double.valueOf(f[24+(pt1*7)+7].getText());
            double sgh=0;double sfh=0;double h2=0;double sfg=0;double sfl=0;double hfl=0;double hfg=0;double h3s=0;double h3=0;double hc=0;double h1=0;
            double vfl=0;double h4s=0;double h4=0;double hc4=0;double it=0;double ci=0;double h22=0;double h23=0;found=false;j=0;
             double sm1=0;double sm2=0;double sfgm=0;double hfgm=0;double hflm=0;double sflm=0;double h22s=0;double sghm=0;
            j=0;a=Double.valueOf(f[(pt*6)+2].getText());b=Double.valueOf(f[114+(pc*5)].getText());tem=Double.valueOf(f[(pt*6)+1].getText());
            a1=Double.valueOf(f[(pt1*6)+2].getText());tem1=Double.valueOf(f[(pt1*6)+1].getText());
            found=false;  
            if(a<a1)
            {
                double temp=0;double tempp=0;
                a1=temp;a1=a;a=temp; tem1=tempp;tem1=tem;tem=tempp;
            }
            int z=0;int zz=0;
            if(tem==-1)
            {
               while(found==false)
                {
                    if(supersteam[j]==a)
                    {
                        found=true;z=j;
                    }
                    else if(j==supersteam.length-1)
                    {
                     found=true;z=j;
                    }
                    j++;
                }
                if(j!=supersteam.length-1)
                {j=0;
                 found=false;
                 tem=supersteam[z+1];
                }  
            }
                while(found==false)
                {
                    if(supersteam[j]==a)
                    {
                        found=true;z=j;
                    }
                    else if(j==supersteam.length-1)
                    {
                     found=true;z=j;
                    }
                    j++;
                }
                if(j!=supersteam.length-1)
                {j=0;
                 found=false;
                 while(found==false)
                 {
                     if(supersteam[z+j]==tem){found=true;zz=z+j;}
                     else if(supersteam[z+j-1]==1000){found=true;zz=z+j;}
                     j++;
                     
                    }
                }
                h2=supersteam[zz+3];sgh=supersteam[zz+4];found=false;j=0;
        
         z=0;zz=0;
                while(found==false)
                {
                    if(supersteam[j]==a1)
                    {
                        found=true;z=j;
                    }
                    else if(j==supersteam.length-1)
                    {
                     found=true;z=j;
                    }
                    j++;
                }
                if(j!=supersteam.length-1)
                {j=0;
                 found=false;
                 while(found==false)
                 {
                     if(supersteam[z+j]==tem1){found=true;zz=z+j;}
                     else if(supersteam[z+j-1]==1000){found=true;zz=z+j;}
                     j++;
                     
                    }
                }
                h23=supersteam[zz+3];sm2=supersteam[zz+4];found=false;j=0;
                while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<a1)
                 {
                 j+=10;
                }
                if(v>=a1)
                {
                    found=true;
                    if(v==a1){sfgm=(steam_sat_p[j+9]-steam_sat_p[j+8]);sflm=steam_sat_p[j+8];hfgm=steam_sat_p[j+7]-steam_sat_p[j+6];
                    hflm=steam_sat_p[j+6];}
                    else
                    {
                       double sfg1=steam_sat_p[j-1]+((steam_sat_p[j+9]-steam_sat_p[j-1])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                       
                         double sfg2=steam_sat_p[j-2]+((steam_sat_p[j+8]-steam_sat_p[j-2])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                         double hfg1=steam_sat_p[j-3]+((steam_sat_p[j+7]-steam_sat_p[j-3])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                         
                         double hfg2=steam_sat_p[j-4]+((steam_sat_p[j+6]-steam_sat_p[j-4])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                         
                         
                         hfgm=(hfg1-hfg2);
                         hflm=hfg2;
                         sfgm=(sfg1-sfg2);
                         sflm=sfg2;
                    }
                }
            }found=false; j=0;
            double x=(sgh-sflm)/sfgm;
            h22s=hflm+x*hfgm;   
            h22=h2-(h2-h22s)*tureff;
            
            found=false;j=0;
             while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<b)
                 {
                 j+=10;
                }
                if(v>=b)
                {
                    found=true;
                    if(v==b){sfg=(steam_sat_p[j+9]-steam_sat_p[j+8]);sfl=steam_sat_p[j+8];vfl=steam_sat_p[j+2]/1000;hfg=steam_sat_p[j+7]-steam_sat_p[j+6];
                    hfl=steam_sat_p[j+6];ci=steam_sat_p[j+1];}
                    else
                    {
                       double sfg1=steam_sat_p[j-1]+((steam_sat_p[j+9]-steam_sat_p[j-1])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                       
                         double sfg2=steam_sat_p[j-2]+((steam_sat_p[j+8]-steam_sat_p[j-2])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         double hfg1=steam_sat_p[j-3]+((steam_sat_p[j+7]-steam_sat_p[j-3])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         
                         double hfg2=steam_sat_p[j-4]+((steam_sat_p[j+6]-steam_sat_p[j-4])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=steam_sat_p[j-8]+((steam_sat_p[j+2]-steam_sat_p[j-8])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         ci=steam_sat_p[j-9]+((steam_sat_p[j+1]-steam_sat_p[j-9])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=vfl/1000;
                         hfg=(hfg1-hfg2);
                         hfl=hfg2;
                         sfg=(sfg1-sfg2);
                         sfl=sfg2;
                    }
                }
            }
            double x11=(sm2-sfl)/sfg;
            h3s=hfl+x11*hfg; 
            h4s=vfl*(a-b)*100+hfl;   h4=((h4s-hfl)/pumeff)+hfl;h3=h2-(h2-h3s)*tureff1;
           
            rankineeff=((h2-h22)+(h23-h3)-(h4-hfl))/((h2-h4)+(h23-h22));
            rankineeff=rankineeff*100;
            double WT1=(h2-h22);
            double WT2=(h23-h3);
            double reheat=(h23-h22);
           double scc=1/((h2-h22)+(h23-h3)-(h4-hfl));scc=scc*1000;
            result="Work done in high pressure turbine "+WT1+"\n"+"Work done in low pressure turbine "+WT2+"\n"+"Amount of reheat required "+reheat+"\n"+"Thermal efficiency of cycle "+rankineeff+"\n"+"condenser inlet temperature "+ci+"\n"+"pump work "+(h4-hfl)+"\n"+"Specific steam consumption "+scc+"\n";
          
            }
            else if(probtypeno==5)
            {
              
           double tureff=Double.valueOf(f[24+(pt*7)+7].getText());  double pumeff=Double.valueOf(f[76+(pp*7)+7].getText());double tureff1=Double.valueOf(f[24+(pt1*7)+7].getText());
            double sgh=0;double sfh=0;double h2=0;double sfg=0;double sfl=0;double hfl=0;double hfg=0;double h3s=0;double h3=0;double hc=0;double h1=0;
            double vfl=0;double h4s=0;double h4=0;double hc4=0;double it=0;double ci=0;double h22=0;double h23=0; found=false;j=0;
             double sm1=0;double sm2=0;double sfgm=0;double hfgm=0;double hflm=0;double sflm=0;double h22s=0;double sghm=0;double rankineeff=100;double vflm=0;double h7=0;
            double bled=0;
             j=0;a=Double.valueOf(f[(pt*6)+2].getText());b=Double.valueOf(f[114+(pc*5)].getText());tem=Double.valueOf(f[(pt*6)+1].getText());
            a1=Double.valueOf(f[(pt1*6)+2].getText());
            found=false;  
            if(a<a1)
            {
                double temp=0;
                a1=temp;a1=a;a=temp;
            }
            int z=0;int zz=0;
            //for saturated ,enter temperature =-1
            if(tem==-1)
            {
               while(found==false)
                {
                    if(supersteam[j]==a)
                    {
                        found=true;z=j;
                    }
                    else if(j==supersteam.length-1)
                    {
                     found=true;z=j;
                    }
                    j++;
                }
                if(j!=supersteam.length-1)
                {j=0;
                 found=false;
                 tem=supersteam[z+1];
                }  
            }
            z=0;zz=0;found=false;
                while(found==false)
                {
                    if(supersteam[j]==a)
                    {
                        found=true;z=j;
                    }
                    else if(j==supersteam.length-1)
                    {
                     found=true;z=j;
                    }
                    j++;
                }
                if(j!=supersteam.length-1)
                {j=0;
                 found=false;
                 while(found==false)
                 {
                     if(supersteam[z+j]==tem){found=true;zz=z+j;}
                     else if(supersteam[z+j-1]==1000){found=true;zz=z+j;}
                     j++;
                     
                    }
                }
                h2=supersteam[zz+3];sgh=supersteam[zz+4];found=false;j=0;
        
         
                while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<a1)
                 {
                 j+=10;
                }
                if(v>=a1)
                {
                    found=true;
                    if(v==a1){sfgm=(steam_sat_p[j+9]-steam_sat_p[j+8]);sflm=steam_sat_p[j+8];hfgm=steam_sat_p[j+7]-steam_sat_p[j+6];
                    hflm=steam_sat_p[j+6]; vflm=steam_sat_p[j+2]/1000; }
                    else
                    {
                       double sfg1=steam_sat_p[j-1]+((steam_sat_p[j+9]-steam_sat_p[j-1])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                       
                         double sfg2=steam_sat_p[j-2]+((steam_sat_p[j+8]-steam_sat_p[j-2])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                         double hfg1=steam_sat_p[j-3]+((steam_sat_p[j+7]-steam_sat_p[j-3])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                         
                         double hfg2=steam_sat_p[j-4]+((steam_sat_p[j+6]-steam_sat_p[j-4])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                         vflm=steam_sat_p[j-8]+((steam_sat_p[j+2]-steam_sat_p[j-8])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                         vflm=vflm/1000;
                         hfgm=(hfg1-hfg2);
                         hflm=hfg2;
                         sfgm=(sfg1-sfg2);
                         sflm=sfg2;
                    }
                }
            }found=false; j=0;
            double x=(sgh-sflm)/sfgm;
            h22s=hflm+x*hfgm;   
            h22=h2-(h2-h22s)*tureff;
            h7=hflm+vflm*(a-a1)*100;
            
            found=false;j=0;
             while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<b)
                 {
                 j+=10;
                }
                if(v>=b)
                {
                    found=true;
                    if(v==b){sfg=(steam_sat_p[j+9]-steam_sat_p[j+8]);sfl=steam_sat_p[j+8];vfl=steam_sat_p[j+2]/1000;hfg=steam_sat_p[j+7]-steam_sat_p[j+6];
                    hfl=steam_sat_p[j+6];ci=steam_sat_p[j+1];}
                    else
                    {
                       double sfg1=steam_sat_p[j-1]+((steam_sat_p[j+9]-steam_sat_p[j-1])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                       
                         double sfg2=steam_sat_p[j-2]+((steam_sat_p[j+8]-steam_sat_p[j-2])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         double hfg1=steam_sat_p[j-3]+((steam_sat_p[j+7]-steam_sat_p[j-3])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         
                         double hfg2=steam_sat_p[j-4]+((steam_sat_p[j+6]-steam_sat_p[j-4])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=steam_sat_p[j-8]+((steam_sat_p[j+2]-steam_sat_p[j-8])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         ci=steam_sat_p[j-9]+((steam_sat_p[j+1]-steam_sat_p[j-9])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=vfl/1000;
                         hfg=(hfg1-hfg2);
                         hfl=hfg2;
                         sfg=(sfg1-sfg2);
                         sfl=sfg2;
                    }
                }
            }
            double x11=(sgh-sfl)/sfg;
            h3s=hfl+x11*hfg; 
            h4s=vfl*(a-b)*100+hfl;   h4=((h4s-hfl)/pumeff)+hfl;h3=h2-(h2-h3s)*tureff1;
            bled=(hflm-h4)/(h22-h4);
           
            rankineeff=((h2-h22)-(h7-hflm)+(1-bled)*(h22-h3-h4+hfl))/(h2-h7);
            rankineeff=rankineeff*100;
            double WT1=(h2-h22);
            double WT2=(h22-h3);
            double reheat=(h23-h22);
           double scc=1/((h2-h22+hflm-h7)+(1-bled)*(h22-h3-h4+hfl));scc=scc*1000;
            result="Work done in high pressure turbine "+WT1+"\n"+"Work done in low pressure turbine "+WT2+"\n"+"Amount of steam required to be bled "+bled+"\n"+"Thermal efficiency of cycle "+rankineeff+"\n"+"condenser inlet temperature "+ci+"\n"+"Specific steam consumption "+scc+"\n";
            
            }
            else if(probtypeno==6)
            {
              a=0;b=0; tem=0; a1=0;
           double tureff=1;  double pumeff=1;double tureff1=1;
           double pumeff1=1;
            double sgh=0;double sfh=0;double h2=0;double sfg=0;double sfl=0;double hfl=0;double hfg=0;double h3s=0;double h3=0;double hc=0;double h1=0;
            double vfl=0;double h4s=0;double h4=0;double hc4=0;double it=0;double ci=0;double h22=0;double h23=0; found=false;j=0;
             double sm1=0;double sm2=0;double sfgm=0;double hfgm=0;double hflm=0;double sflm=0;double h22s=0;double sghm=0;double rankineeff=100;double vflm=0;double h7=0;
            double bled=0;double he=0;double sghmer=0;
            double h2mer=0;double sfhmer=0;double h1mer=0;double itmer=0;double sfgmer=0;double sflmer=0;double vflmer=0;double hfgmer=0;double hflmer=0;double cimer=0;
             j=0;a=30;b=0.1;tem=250;
           double amer=4.5;double bmer=0.08;
           a=Double.valueOf(f[(pt*6)+2].getText());b=Double.valueOf(f[114+(pc*5)].getText());tem=Double.valueOf(f[(pt*6)+1].getText());
            amer=Double.valueOf(f[(ptm*6)+2].getText());
            bmer=Double.valueOf(f[114+(pcm*5)].getText());
            found=false;  
            
            
             while(found==false)
             {
                 double v=merc[j];
                 if(v<amer)
                 {
                 j+=10;
                }
                if(v>=amer)
                {
                    found=true;
                    if(v==amer)
                    {sghmer=merc[j+9];h2mer=merc[j+7];sfhmer=merc[j+8];h1mer=merc[j+6];itmer=merc[j+1];}
                    else 
                    {
                        sghmer=merc[j-1]+((merc[j+9]-merc[j-1])/(merc[j]-merc[j-10]))*(amer-merc[j-10]);
                        sfhmer=merc[j-2]+((merc[j+8]-merc[j-2])/(merc[j]-merc[j-10]))*(amer-merc[j-10]);
                       
                        h2mer=merc[j-3]+((merc[j+7]-merc[j-3])/(merc[j]-merc[j-10]))*(amer-merc[j-10]);
                        h1mer=merc[j-4]+((merc[j+6]-merc[j-4])/(merc[j]-merc[j-10]))*(amer-merc[j-10]);
                        itmer=merc[j-9]+((merc[j+1]-merc[j-9])/(merc[j]-merc[j-10]))*(amer-merc[j-10]);
                    }
                }
            }
            found=false;j=0;
             while(found==false)
             {
                 double v=merc[j];
                 if(v<bmer)
                 {
                 j+=10;
                }
                if(v>=bmer)
                {
                    found=true;
                    if(v==bmer){sfgmer=(merc[j+9]-merc[j+8]);sflmer=merc[j+8];vflmer=merc[j+2]/1000;hfgmer=merc[j+7]-merc[j+6];
                    hflmer=merc[j+6];cimer=merc[j+1];}
                    else
                    {
                       double sfg1=merc[j-1]+((merc[j+9]-merc[j-1])/(merc[j]-merc[j-10]))*(bmer-merc[j-10]);
                       
                         double sfg2=merc[j-2]+((merc[j+8]-merc[j-2])/(merc[j]-merc[j-10]))*(bmer-merc[j-10]);
                         double hfg1=merc[j-3]+((merc[j+7]-merc[j-3])/(merc[j]-merc[j-10]))*(bmer-merc[j-10]);
                         
                         double hfg2=merc[j-4]+((merc[j+6]-merc[j-4])/(merc[j]-merc[j-10]))*(bmer-merc[j-10]);
                         vflmer=merc[j-8]+((merc[j+2]-merc[j-8])/(merc[j]-merc[j-10]))*(bmer-merc[j-10]);
                         cimer=merc[j-9]+((merc[j+1]-merc[j-9])/(merc[j]-merc[j-10]))*(bmer-merc[j-10]);
                         //vflmer=vflmer/1000;
                         hfgmer=(hfg1-hfg2);
                         hflmer=hfg2;
                         sfgmer=(sfg1-sfg2);
                         sflmer=sfg2;
                    }
                }
            }
            double x22=(sghmer-sflmer)/sfgmer;
            double h3smer=hflmer+x22*hfgmer;   
            
            
            double h4smer=vflmer*(amer-bmer)*100+hflmer;   double h4mer=((h4smer-hflmer)/pumeff)+hflmer;double h3mer=h2mer-(h2mer-h3smer)*tureff;
            
            
            
            int z=0;int zz=0; j=0;found=false;
                while(found==false)
                {
                    if(supersteam[j]==a)
                    {
                        found=true;z=j;
                    }
                    else if(j==supersteam.length-1)
                    {
                     found=true;z=j;
                    }
                    j++;
                }
                if(j!=supersteam.length-1)
                {j=0;
                 found=false;
                 while(found==false)
                 {
                     if(supersteam[z+j]==tem){found=true;zz=z+j;}
                     else if(supersteam[z+j-1]==1000){found=true;zz=z+j;}
                     j++;
                     
                    }
                }
                h2=supersteam[zz+3];sgh=supersteam[zz+4];found=false;j=0;
        
         
                while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<a)
                 {
                 j+=10;
                }
                if(v>=a)
                {
                    found=true;
                    if(v==a){
                    he=steam_sat_p[j+7];  }
                    else
                    {
                       
                         he=steam_sat_p[j-3]+((steam_sat_p[j+7]-steam_sat_p[j-3])/(steam_sat_p[j]-steam_sat_p[j-10]))*(a1-steam_sat_p[j-10]);
                         
                      
                    }
                }
            }found=false; j=0;
           
            
            found=false;j=0;
             while(found==false)
             {
                 double v=steam_sat_p[j];
                 if(v<b)
                 {
                 j+=10;
                }
                if(v>=b)
                {
                    found=true;
                    if(v==b){sfg=(steam_sat_p[j+9]-steam_sat_p[j+8]);sfl=steam_sat_p[j+8];vfl=steam_sat_p[j+2]/1000;hfg=steam_sat_p[j+7]-steam_sat_p[j+6];
                    hfl=steam_sat_p[j+6];ci=steam_sat_p[j+1];}
                    else
                    {
                       double sfg1=steam_sat_p[j-1]+((steam_sat_p[j+9]-steam_sat_p[j-1])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                       
                         double sfg2=steam_sat_p[j-2]+((steam_sat_p[j+8]-steam_sat_p[j-2])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         double hfg1=steam_sat_p[j-3]+((steam_sat_p[j+7]-steam_sat_p[j-3])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         
                         double hfg2=steam_sat_p[j-4]+((steam_sat_p[j+6]-steam_sat_p[j-4])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=steam_sat_p[j-8]+((steam_sat_p[j+2]-steam_sat_p[j-8])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         ci=steam_sat_p[j-9]+((steam_sat_p[j+1]-steam_sat_p[j-9])/(steam_sat_p[j]-steam_sat_p[j-10]))*(b-steam_sat_p[j-10]);
                         vfl=vfl/1000;
                         hfg=(hfg1-hfg2);
                         hfl=hfg2;
                         sfg=(sfg1-sfg2);
                         sfl=sfg2;
                    }
                }
            }
            double x=(sgh-sfl)/sfg;
            h3s=hfl+x*hfg;   
           
            h4s=vfl*(a-b)*100+hfl;   h4=((h4s-hfl)/pumeff1)+hfl;h3=h2-(h2-h3s)*tureff1;
           double mhg=(he-h4)/(h3mer-hflmer);
           double WT1=(h2mer-h3mer)*mhg;
           double WT2=h2-h3;
           double reff=(mhg*(h2mer-h3mer)+(h2-h3)-mhg*(h4mer-hflmer)-(h4-hfl))/(mhg*(h2mer-h4mer)+(h2-he));
           double scc=1/(mhg*(h2mer-h3mer)+(h2-h3)-mhg*(h4mer-hflmer)-(h4-hfl));scc=scc*1000;
            result="Work done in high pressure turbine "+WT1+"\n"+"Work done in low pressure turbine "+WT2+"\n"+"Amount of mercury required per kg of steam "+mhg+"\n"+"Thermal efficiency of cycle "+reff+"\n"+"Specific steam consumption "+scc+"\n";
          
          
            }
        
            
                
                
              
            
            
        
invalidate();
validate();
}

public void itemStateChanged(ItemEvent e) 
                  { 
                    
                     if(e.getSource()==process) 
                        { 
                          if(process.getSelectedItem()=="NEW TURBINE") 
                            { 
                               turb=true;
                               pump=false;
                               boil=false;
                               cond=false;
                                }  
                                 if(process.getSelectedItem()=="NEW FEED PUMP") 
                            { 
                               turb=false;
                               pump=true;
                               boil=false;
                               cond=false;
                                }  
                                if(process.getSelectedItem()=="NEW CONDENSER") 
                            { 
                               turb=false;
                               pump=false;
                               cond=true;
                               boil=false;
                             
                                }  
                                if(process.getSelectedItem()=="NEW BOILER") 
                            { 
                               turb=false;
                               pump=false;
                               boil=true;
                               cond=false;
                                } 
                            }
                           if(e.getSource()==prob_type)
                           {
                               if(prob_type.getSelectedItem()=="TYPE 1")
                                {
                            probno=1;
                        }
                        if(prob_type.getSelectedItem()=="TYPE 2")
                                {
                            probno=2;
                        }
                        if(prob_type.getSelectedItem()=="TYPE 3")
                                {
                            probno=3;
                        }
                        if(prob_type.getSelectedItem()=="TYPE 4")
                                {
                            probno=4;
                        }
                        if(prob_type.getSelectedItem()=="TYPE 5")
                                {
                            probno=5;
                        }
                        if(prob_type.getSelectedItem()=="TYPE 6")
                                {
                            probno=6;
                        }
                       
}

}
}
 
  
  
