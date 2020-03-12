/**
 *
 * @author StormTiberius
 */

package cde.core;

import com.google.common.collect.ImmutableMap;
import java.awt.Color;
import java.util.Map;

public class Defaults
{
    // BLOCK TEXTURE INDEX
    public static final int TEXTURE_BLOCK_ORE_RUBY = 0;
    public static final int TEXTURE_BLOCK_ORE_JADE = 1;
    public static final int TEXTURE_BLOCK_ORE_SAPPHIRE = 2;
    public static final int TEXTURE_BLOCK_ORE_COPPER = 3;
    public static final int TEXTURE_BLOCK_ORE_TIN = 4;
    public static final int TEXTURE_BLOCK_ORE_SILVER = 5;
    public static final int TEXTURE_BLOCK_ORE_LEAD = 6;
    public static final int TEXTURE_BLOCK_ORE_ZINC = 7;
    public static final int TEXTURE_BLOCK_ORE_URANIUM = 8;
    public static final int TEXTURE_BLOCK_ORE_APATITE = 9;
    public static final int TEXTURE_BLOCK_ORE_BITUMEN = 10;
    public static final int TEXTURE_BLOCK_ORE_SULFUR = 11;
    public static final int TEXTURE_BLOCK_ORE_SALTPETER = 12;
    public static final int TEXTURE_BLOCK_ORE_ONYX = 13;
    public static final int TEXTURE_BLOCK_ORE_PHOENIXITE = 14;
    public static final int TEXTURE_BLOCK_ORE_QUARTZ = 15;
    
    public static final int TEXTURE_BLOCK_STORAGE_RUBY = 16;
    public static final int TEXTURE_BLOCK_STORAGE_JADE = 17;
    public static final int TEXTURE_BLOCK_STORAGE_SAPPHIRE = 18;
    public static final int TEXTURE_BLOCK_STORAGE_COPPER = 19;
    public static final int TEXTURE_BLOCK_STORAGE_TIN = 20;
    public static final int TEXTURE_BLOCK_STORAGE_SILVER = 21;
    public static final int TEXTURE_BLOCK_STORAGE_LEAD = 22;
    public static final int TEXTURE_BLOCK_STORAGE_ZINC = 23;
    public static final int TEXTURE_BLOCK_STORAGE_URANIUM = 24;
    public static final int TEXTURE_BLOCK_STORAGE_BRONZE = 25;
    public static final int TEXTURE_BLOCK_STORAGE_BRASS = 26;
    public static final int TEXTURE_BLOCK_STORAGE_STEEL = 27;
    public static final int TEXTURE_MACHINE_SOLAR_PANEL = 28;
    public static final int TEXTURE_BLOCK_SMOKE_STACK_BOTTOM = 29;
    public static final int TEXTURE_BLOCK_SMOKE_STACK_TOP = 30;
    public static final int TEXTURE_BLOCK_SMOKE_STACK_SIDE = 31;
    
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_00 = 32;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_01 = 33;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_02 = 34;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_03 = 35;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_04 = 36;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_05 = 37;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_06 = 38;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_07 = 39;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_08 = 40;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_09 = 41;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_10 = 42;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_11 = 43;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_OFF_12 = 44;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_TOP = 45;
    public static final int TEXTURE_MACHINE_BATTERY_STATION_LV_OFF = 46;
    public static final int TEXTURE_MACHINE_BATTERY_STATION_LV_ON = 47;
    
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_00 = 48;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_01 = 49;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_02 = 50;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_03 = 51;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_04 = 52;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_05 = 53;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_06 = 54;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_07 = 55;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_08 = 56;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_09 = 57;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_10 = 58;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_11 = 59;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_LV_ON_12 = 60;
    public static final int TEXTURE_MACHINE_ELECTRIC_CONNECTOR_LV_0 = 61;
    public static final int TEXTURE_MACHINE_ELECTRIC_CONNECTOR_LV_1 = 62;
    public static final int TEXTURE_MACHINE_ELECTRIC_CONNECTOR_LV_2 = 63;
    
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_00 = 64;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_01 = 65;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_02 = 66;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_03 = 67;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_04 = 68;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_05 = 69;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_06 = 70;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_07 = 71;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_08 = 72;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_09 = 73;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_10 = 74;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_11 = 75;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_OFF_12 = 76;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_TOP = 77;
    public static final int TEXTURE_MACHINE_BATTERY_STATION_MV_OFF = 78;
    public static final int TEXTURE_MACHINE_BATTERY_STATION_MV_ON = 79;
    
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_00 = 80;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_01 = 81;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_02 = 82;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_03 = 83;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_04 = 84;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_05 = 85;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_06 = 86;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_07 = 87;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_08 = 88;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_09 = 89;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_10 = 90;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_11 = 91;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_MV_ON_12 = 92;
    public static final int TEXTURE_MACHINE_ELECTRIC_CONNECTOR_MV_0 = 93;
    public static final int TEXTURE_MACHINE_ELECTRIC_CONNECTOR_MV_1 = 94;
    public static final int TEXTURE_MACHINE_ELECTRIC_CONNECTOR_MV_2 = 95;
    
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_00 = 96;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_01 = 97;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_02 = 98;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_03 = 99;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_04 = 100;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_05 = 101;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_06 = 102;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_07 = 103;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_08 = 104;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_09 = 105;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_10 = 106;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_11 = 107;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_OFF_12 = 108;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_TOP = 109;
    public static final int TEXTURE_MACHINE_BATTERY_STATION_HV_OFF = 110;
    public static final int TEXTURE_MACHINE_BATTERY_STATION_HV_ON = 111;
    
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_00 = 112;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_01 = 113;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_02 = 114;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_03 = 115;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_04 = 116;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_05 = 117;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_06 = 118;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_07 = 119;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_08 = 120;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_09 = 121;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_10 = 122;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_11 = 123;
    public static final int TEXTURE_MACHINE_CHARGING_BENCH_HV_ON_12 = 124;
    public static final int TEXTURE_MACHINE_ELECTRIC_CONNECTOR_HV_0 = 125;
    public static final int TEXTURE_MACHINE_ELECTRIC_CONNECTOR_HV_1 = 126;
    public static final int TEXTURE_MACHINE_ELECTRIC_CONNECTOR_HV_2 = 127;
    
    public static final int TEXTURE_MACHINE_GRATE_SIDE = 48;
    public static final int TEXTURE_MACHINE_GRATE_BOTTOM_TOP = 49;
    public static final int TEXTURE_MACHINE_SPEAKER_BOTTOM_TOP = 50;
    public static final int TEXTURE_MACHINE_SPEAKER_SIDE = 51;
    
    public static final int TEXTURE_DRUM_IRON_SIDE = 52;
    public static final int TEXTURE_DRUM_STEEL_SIDE = 53;
    public static final int TEXTURE_DRUM_IRON_TOP = 54;
    public static final int TEXTURE_DRUM_STEEL_TOP = 55;
    
    public static final int TEXTURE_MACHINE_SMOKESTACK_BOTTOM = 56;
    public static final int TEXTURE_MACHINE_SMOKESTACK_TOP = 57;
    public static final int TEXTURE_MACHINE_SMOKESTACK_SIDE = 58;
    
    public static final int TEXTURE_MACHINE_ELECTRIC_TOP = 64;
    public static final int TEXTURE_MACHINE_ELECTRIC_SIDE = 65;
    public static final int TEXTURE_MACHINE_ELECTRIC_INPUT = 66;
    public static final int TEXTURE_MACHINE_ELECTRIC_OUTPUT = 67;
    public static final int TEXTURE_MACHINE_ELECTRIC_GENERATOR = 68;
    public static final int TEXTURE_MACHINE_ELECTRIC_FURNACE = 69;
    public static final int TEXTURE_MACHINE_ELECTRIC_BATTERY = 70;
    
    // ITEM TEXTURE INDEX
    public static final int TEXTURE_MATERIAL_INGOT_COPPER = 0;
    public static final int TEXTURE_MATERIAL_INGOT_TIN = 1;
    public static final int TEXTURE_MATERIAL_INGOT_SILVER = 2;
    public static final int TEXTURE_MATERIAL_INGOT_LEAD = 3;
    public static final int TEXTURE_MATERIAL_INGOT_URANIUM = 4;
    public static final int TEXTURE_MATERIAL_INGOT_ZINC = 5;
    public static final int TEXTURE_MATERIAL_INGOT_BRONZE = 6;
    public static final int TEXTURE_MATERIAL_INGOT_BRASS = 7;
    public static final int TEXTURE_MATERIAL_INGOT_STEEL = 8;
    public static final int TEXTURE_MATERIAL_INGOT_PEAT = 9;
    public static final int TEXTURE_MATERIAL_INGOT_BITUMINOUS_PEAT = 10;
    
    public static final int TEXTURE_MATERIAL_DUST_COAL = 16;
    public static final int TEXTURE_MATERIAL_DUST_CHARCOAL = 17;
    public static final int TEXTURE_MATERIAL_DUST_IRON = 18;
    public static final int TEXTURE_MATERIAL_DUST_GOLD = 19;
    public static final int TEXTURE_MATERIAL_DUST_COPPER = 20;
    public static final int TEXTURE_MATERIAL_DUST_TIN = 21;
    public static final int TEXTURE_MATERIAL_DUST_SILVER = 22;
    public static final int TEXTURE_MATERIAL_DUST_LEAD = 23;
    public static final int TEXTURE_MATERIAL_DUST_ZINC = 24;
    public static final int TEXTURE_MATERIAL_DUST_BRONZE = 25;
    public static final int TEXTURE_MATERIAL_DUST_BRASS = 26;
    public static final int TEXTURE_MATERIAL_DUST_STEEL = 27;
    public static final int TEXTURE_MATERIAL_DUST_SULFUR = 28;
    public static final int TEXTURE_MATERIAL_DUST_SALTPETER = 29;
    public static final int TEXTURE_MATERIAL_DUST_QUARTZ = 30;
    public static final int TEXTURE_MATERIAL_DUST_APATITE = 31;
    
    public static final int TEXTURE_MATERIAL_NUGGET_IRON = 32;
    public static final int TEXTURE_MATERIAL_NUGGET_COPPER = 33;
    public static final int TEXTURE_MATERIAL_NUGGET_TIN = 34;
    public static final int TEXTURE_MATERIAL_NUGGET_SILVER = 35;
    public static final int TEXTURE_MATERIAL_NUGGET_LEAD = 36;
    public static final int TEXTURE_MATERIAL_NUGGET_ZINC = 37;
    public static final int TEXTURE_MATERIAL_NUGGET_BRONZE = 38;
    public static final int TEXTURE_MATERIAL_NUGGET_BRASS = 39;
    public static final int TEXTURE_MATERIAL_NUGGET_STEEL = 40;
    
    public static final int TEXTURE_MATERIAL_GEM_QUARTZ = 48;
    public static final int TEXTURE_MATERIAL_GEM_APATITE = 49;
    public static final int TEXTURE_MATERIAL_GEM_RUBY = 50;
    public static final int TEXTURE_MATERIAL_GEM_JADE = 51;
    public static final int TEXTURE_MATERIAL_GEM_SAPPHIRE = 52;
    
    public static final int TEXTURE_MATERIAL_FUEL_COKE = 64;
    
    public static final int TEXTURE_MATERIAL_PLATE_IRON = 80;
    public static final int TEXTURE_MATERIAL_PLATE_GOLD = 81;
    public static final int TEXTURE_MATERIAL_PLATE_COPPER = 82;
    public static final int TEXTURE_MATERIAL_PLATE_TIN = 83;
    public static final int TEXTURE_MATERIAL_PLATE_BRONZE = 84;
    public static final int TEXTURE_MATERIAL_PLATE_STEEL = 85;
    
    public static final int TEXTURE_MATERIAL_CIRCUIT_BOARD_SINGLE = 96;
    public static final int TEXTURE_MATERIAL_CIRCUIT_BOARD_DOUBLE = 97;
    public static final int TEXTURE_MATERIAL_CIRCUIT_BOARD_MULTI = 98;
    public static final int TEXTURE_MATERIAL_ELECTRIC_MOTOR = 99;
    public static final int TEXTURE_MATERIAL_ELECTRIC_WIRE = 100;
    
    // BLOCK ID
    public static final int BLOCK_ORE_ID = 180;
    public static final int BLOCK_STORAGE_ID = 181;
    public static final int BLOCK_A_ID = 182;
    public static final int BLOCK_B_ID = 183;
    public static final int BLOCK_INDUSTRY_DRUM_ID = 184;
    public static final int BLOCK_INDUSTRY_ALPHA_ID = 185;
    public static final int BLOCK_INDUSTRY_BETA_ID = 186;
    public static final int BLOCK_INDUSTRY_GAMMA_ID = 187;
    public static final int BLOCK_INDUSTRY_DELTA_ID = 188;
    public static final int BLOCK_INDUSTRY_MISC_ID = 189;
    
    // ITEM ID
    public static final int ITEM_MATERIALS_ID = 500;
    public static final int ITEM_NIGHT_VISION_GOGGLES_ID = 501;
    
    // FUEL VALUES
    public static final int PEAT_FUEL_VALUE = 2000;
    public static final int BITUMINOUS_PEAT_FUEL_VALUE = 4200;
    public static final int COKE_FUEL_VALUE = 6400;
    
    // WORLD GEN DEFAULTS
    public static final int[][] ORE_GEN_DEFAULTS =
    {    
        new int[]{1,10,15,10,70}, // Copper
        new int[]{1,6,25,0,40}, // Tin
        new int[]{1,8,4,0,32}, // Silver
        new int[]{1,8,4,10,35}, // Lead
        new int[]{1,8,4,10,35}, // Zinc
        new int[]{1,3,20,0,64}, // Uranium
        new int[]{1,3,20,0,64}, // Bitumen
        new int[]{1,6,1,10,16}, // Sulfur
        new int[]{1,6,1,16,32}, // Saltpeter
        new int[]{1,6,1,0,64}, // Apatite
        new int[]{1,7,2,0,48}, // Ruby
        new int[]{1,7,2,0,48}, // Jade
        new int[]{1,7,2,0,48}, // Sapphire
        new int[]{1,4,7,12,62}, // Onyx
        new int[]{1,4,7,12,62}, // Phoenixite
        new int[]{1,4,7,12,62}, // Quartz
    };
    
    public static final int[][] ORE_GEN_DEFAULTS_TERRENE =
    {    
        new int[]{1,10,30,20,140}, // Copper
        new int[]{1,6,50,0,80}, // Tin
        new int[]{1,8,8,0,64}, // Silver
        new int[]{1,8,8,20,70}, // Lead
        new int[]{1,8,8,20,70}, // Zinc
        new int[]{1,3,40,0,128}, // Uranium
        new int[]{1,3,40,0,128}, // Bitumen
        new int[]{1,6,2,20,32}, // Sulfur
        new int[]{1,6,2,32,64}, // Saltpeter
        new int[]{1,6,2,0,128}, // Apatite
        new int[]{1,7,4,0,96}, // Ruby
        new int[]{1,7,4,0,96}, // Jade
        new int[]{1,7,4,0,96}, // Sapphire
        new int[]{1,4,14,24,124}, // Onyx
        new int[]{1,4,14,24,124}, // Phoenixite
        new int[]{1,4,14,24,124}, // Quartz
    };
    
    // LIQUID DRUM CAPACITY DEFAULTS
    public static final int DRUM_CAPACITY_IRON = 100000;
    public static final int DRUM_CAPACITY_STEEL = 200000;
    
    public static final String[] PAINT_BRUSH_NAMES = {"Black Paint Brush",
                                                      "Red Paint Brush",
                                                      "Green Paint Brush",
                                                      "Brown Paint Brush",
                                                      "Blue Paint Brush",
                                                      "Purple Paint Brush",
                                                      "Cyan Paint Brush",
                                                      "Light Gray Paint Brush",
                                                      "Gray Paint Brush",
                                                      "Pink Paint Brush",
                                                      "Lime Paint Brush",
                                                      "Yellow Paint Brush",
                                                      "Light Blue Paint Brush",
                                                      "Magenta Paint Brush",
                                                      "Orange Paint Brush",
                                                      "White Paint Brush"};
    
    public static final String[] COLOR_NAMES = {"Black",
                                                "Red",
                                                "Green",
                                                "Brown",
                                                "Blue",
                                                "Purple",
                                                "Cyan",
                                                "Light Gray",
                                                "Gray",
                                                "Pink",
                                                "Lime",
                                                "Yellow",
                                                "Light Blue",
                                                "Magenta",
                                                "Orange",
                                                "White"};
    
    public static final String[] DYE_ORE_DICTIONARY_NAMES = {"dyeBlack",
                                                             "dyeRed",
                                                             "dyeGreen",   
                                                             "dyeBrown",                                    
                                                             "dyeBlue",                                            
                                                             "dyePurple",
                                                             "dyeCyan",
                                                             "dyeLightGray",
                                                             "dyeGray",
                                                             "dyePink",
                                                             "dyeLime",
                                                             "dyeYellow",
                                                             "dyeLightBlue",
                                                             "dyeMagenta",
                                                             "dyeOrange",
                                                             "dyeWhite"};

    public static final Color COLOR_BLACK = new Color(26, 26, 26);
    public static final Color COLOR_RED = new Color(153, 51, 51);
    public static final Color COLOR_GREEN = new Color(102, 128, 51);
    public static final Color COLOR_BROWN = new Color(102, 77, 51);
    public static final Color COLOR_BLUE = new Color(51, 77, 179);
    public static final Color COLOR_PURPLE = new Color(128, 64, 179);
    public static final Color COLOR_CYAN = new Color(77, 128, 153);
    public static final Color COLOR_LIGHT_GRAY = new Color(153, 153, 153);
    public static final Color COLOR_GRAY = new Color(77, 77, 77);
    public static final Color COLOR_PINK = new Color(242, 128, 166);
    public static final Color COLOR_LIME = new Color(128, 204, 26);
    public static final Color COLOR_YELLOW = new Color(230, 230, 51);
    public static final Color COLOR_LIGHT_BLUE = new Color(102, 153, 217);
    public static final Color COLOR_MAGENTA = new Color(179, 77, 217);
    public static final Color COLOR_ORANGE = new Color(217, 128, 51);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);
    
    public static final Color[] MINECRAFT_COLORS = {COLOR_BLACK,
                                                    COLOR_RED,
                                                    COLOR_GREEN,
                                                    COLOR_BROWN,
                                                    COLOR_BLUE,
                                                    COLOR_PURPLE,
                                                    COLOR_CYAN,
                                                    COLOR_LIGHT_GRAY,
                                                    COLOR_GRAY,
                                                    COLOR_PINK,
                                                    COLOR_LIME,
                                                    COLOR_YELLOW,
                                                    COLOR_LIGHT_BLUE,
                                                    COLOR_MAGENTA,
                                                    COLOR_ORANGE,
                                                    COLOR_WHITE};
    
    public static final Map<String, Color> MC_COLORS = ImmutableMap.<String, Color>builder()
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[0], COLOR_BLACK)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[1], COLOR_RED)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[2], COLOR_GREEN)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[3], COLOR_BROWN)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[4], COLOR_BLUE)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[5], COLOR_PURPLE)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[6], COLOR_CYAN)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[7], COLOR_LIGHT_GRAY)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[8], COLOR_GRAY)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[9], COLOR_PINK)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[10], COLOR_LIME)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[11], COLOR_YELLOW)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[12], COLOR_LIGHT_BLUE)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[13], COLOR_MAGENTA)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[14], COLOR_ORANGE)
                                                                                            .put(DYE_ORE_DICTIONARY_NAMES[15], COLOR_WHITE).build();

    public static final Color COLOR_MEAD = new Color(220,190,115);
    public static final Color COLOR_COOLANT = new Color(50,210,255);
    public static final Color COLOR_BIOMASS = new Color(105,200,60);
    public static final Color COLOR_STEAM = new Color(138,138,138);
    public static final Color COLOR_BIOFUEL = new Color(238,140,20);
    public static final Color COLOR_WATER = new Color(47,68,245);
    public static final Color COLOR_CREOSOTE = new Color(105,105,10);
    public static final Color COLOR_HONEY = new Color(235,200,60);
    public static final Color COLOR_OIL = new Color(50,50,50);
    public static final Color COLOR_ICE = new Color(198,253,253);
    public static final Color COLOR_MILK = new Color(245,245,245);
    public static final Color COLOR_SEEDOIL = new Color(225,225,150);
    public static final Color COLOR_JUICE = new Color(145,200,70);
    public static final Color COLOR_LAVA = new Color(213,91,19);
    public static final Color COLOR_FUEL = new Color(200,200,5);
    public static final Color COLOR_GLASS = new Color(224,224,224);
    public static final Color COLOR_DEFAULT = new Color(203,205,205);
}
