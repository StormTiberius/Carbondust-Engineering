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
    public static final int TEXTURE_ORE_RUBY = 0;
    public static final int TEXTURE_ORE_JADE = 1;
    public static final int TEXTURE_ORE_SAPPHIRE = 2;
    public static final int TEXTURE_ORE_COPPER = 3;
    public static final int TEXTURE_ORE_TIN = 4;
    public static final int TEXTURE_ORE_SILVER = 5;
    public static final int TEXTURE_ORE_LEAD = 6;
    public static final int TEXTURE_ORE_URANIUM = 7;
    public static final int TEXTURE_ORE_SULFUR = 8;
    public static final int TEXTURE_ORE_SALTPETER = 9;
    public static final int TEXTURE_ORE_APATITE = 10;
    public static final int TEXTURE_ORE_QUARTZ = 11;
    
    public static final int TEXTURE_STORAGE_RUBY = 16;
    public static final int TEXTURE_STORAGE_JADE = 17;
    public static final int TEXTURE_STORAGE_SAPPHIRE = 18;
    public static final int TEXTURE_STORAGE_COPPER = 19;
    public static final int TEXTURE_STORAGE_TIN = 20;
    public static final int TEXTURE_STORAGE_SILVER = 21;
    public static final int TEXTURE_STORAGE_LEAD = 22;
    public static final int TEXTURE_STORAGE_URANIUM = 23;
    public static final int TEXTURE_STORAGE_ZINC = 24;
    public static final int TEXTURE_STORAGE_BRONZE = 25;
    public static final int TEXTURE_STORAGE_BRASS = 26;
    public static final int TEXTURE_STORAGE_STEEL = 27;
    
    public static final int TEXTURE_MACHINE_SIDE = 32;
    public static final int TEXTURE_MACHINE_BOTTOM = 33;
    public static final int TEXTURE_MACHINE_CONNECTED = 34;
    public static final int TEXTURE_MACHINE_GENERATOR_SIDE_OFF = 35;
    public static final int TEXTURE_MACHINE_GENERATOR_SIDE_ON = 36;
    public static final int TEXTURE_MACHINE_TURBINE_SIDE = 37;
    public static final int TEXTURE_MACHINE_TURBINE_TOP_OFF = 38;
    public static final int TEXTURE_MACHINE_TURBINE_TOP_ON = 39;
    public static final int TEXTURE_MACHINE_HEATER_TOP = 40;
    public static final int TEXTURE_MACHINE_PUMP_SIDE_OFF = 41;
    public static final int TEXTURE_MACHINE_PUMP_SIDE_ON = 42;
    public static final int TEXTURE_MACHINE_MIXER_SIDE_OFF = 43;
    public static final int TEXTURE_MACHINE_MIXER_SIDE_ON = 44;
    public static final int TEXTURE_MACHINE_SOLAR_PANEL_TOP = 45;
    public static final int TEXTURE_MACHINE_TRANSFORMER_TOP_OFF = 46;
    public static final int TEXTURE_MACHINE_TRANSFORMER_TOP_ON = 47;
    
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
    
    public static final int TEXTURE_MATERIAL_WASHED_IRON = 48;
    public static final int TEXTURE_MATERIAL_WASHED_GOLD = 49;
    public static final int TEXTURE_MATERIAL_WASHED_COPPER = 50;
    public static final int TEXTURE_MATERIAL_WASHED_TIN = 51;
    public static final int TEXTURE_MATERIAL_WASHED_SILVER = 52;
    public static final int TEXTURE_MATERIAL_WASHED_LEAD = 53;
    public static final int TEXTURE_MATERIAL_WASHED_URANIUM = 54;
    
    public static final int TEXTURE_MATERIAL_CRUSHED_IRON = 64;
    public static final int TEXTURE_MATERIAL_CRUSHED_GOLD = 65;
    public static final int TEXTURE_MATERIAL_CRUSHED_COPPER = 66;
    public static final int TEXTURE_MATERIAL_CRUSHED_TIN = 67;
    public static final int TEXTURE_MATERIAL_CRUSHED_SILVER = 68;
    public static final int TEXTURE_MATERIAL_CRUSHED_LEAD = 69;
    public static final int TEXTURE_MATERIAL_CRUSHED_URANIUM = 70;
    
    public static final int TEXTURE_MATERIAL_GEM_RUBY = 80;
    public static final int TEXTURE_MATERIAL_GEM_JADE = 81;
    public static final int TEXTURE_MATERIAL_GEM_SAPPHIRE = 82;
    public static final int TEXTURE_MATERIAL_GEM_QUARTZ = 83;
    public static final int TEXTURE_MATERIAL_GEM_APATITE = 84;
    
    public static final int TEXTURE_MATERIAL_FUEL_COKE = 96;
    
    public static final int TEXTURE_MATERIAL_PLATE_IRON = 112;
    public static final int TEXTURE_MATERIAL_PLATE_GOLD = 113;
    public static final int TEXTURE_MATERIAL_PLATE_COPPER = 114;
    public static final int TEXTURE_MATERIAL_PLATE_TIN = 115;
    public static final int TEXTURE_MATERIAL_PLATE_BRONZE = 116;
    public static final int TEXTURE_MATERIAL_PLATE_STEEL = 117;
    
    public static final int TEXTURE_MATERIAL_CIRCUIT_BOARD_SINGLE = 128;
    public static final int TEXTURE_MATERIAL_CIRCUIT_BOARD_DOUBLE = 129;
    public static final int TEXTURE_MATERIAL_CIRCUIT_BOARD_MULTI = 130;
    public static final int TEXTURE_MATERIAL_ELECTRIC_MOTOR = 131;
    public static final int TEXTURE_MATERIAL_ELECTRIC_WIRE = 132;
    
    public static final int TEXTURE_MATERIAL_BATTERY_LV = 144;
    public static final int TEXTURE_MATERIAL_BATTERY_MV = 145;
    public static final int TEXTURE_MATERIAL_BATTERY_HV = 146;
    public static final int TEXTURE_MATERIAL_CAPACITOR_LV = 147;
    public static final int TEXTURE_MATERIAL_CAPACITOR_MV = 148;
    public static final int TEXTURE_MATERIAL_CAPACITOR_HV = 149;
    
    public static final int TEXTURE_TOOL_WATERING_CAN = 160;
    public static final int TEXTURE_TOOL_WRENCH = 161;
    
    // BLOCK ID
    public static final int BLOCK_ORE_ID = 180;
    public static final int BLOCK_STORAGE_ID = 181;
    public static final int BLOCK_MACHINERY_ALPHA_ID = 182;
    public static final int BLOCK_GRATE_ID = 183;
    public static final int BLOCK_SPEAKER_ID = 184;
    public static final int BLOCK_INDUSTRY_DRUM_ID = 185;
    public static final int BLOCK_INDUSTRY_A_ID = 186;
    public static final int BLOCK_INDUSTRY_B_ID = 187;
    public static final int BLOCK_INDUSTRY_C_ID = 188;
    public static final int BLOCK_INDUSTRY_D_ID = 189;
    
    // ITEM ID
    public static final int ITEM_MATERIALS_ID = 500;
    public static final int ITEM_GOGGLES_ID = 501;
    public static final int ITEM_WATERING_CAN_ID = 502;
    
    // FUEL VALUES
    public static final int PEAT_FUEL_VALUE = 2000;
    public static final int BITUMINOUS_PEAT_FUEL_VALUE = 4200;
    public static final int COKE_FUEL_VALUE = 6400;
    
    // WORLD GEN DEFAULTS
    public static final int[][] ORE_GEN_DEFAULTS =
    {    
        new int[]{1,10,15,10,70},
        new int[]{1,6,25,0,40},
        new int[]{1,8,4,0,32},
        new int[]{1,8,4,10,35},        
        new int[]{1,3,20,0,64},
        new int[]{1,6,1,10,16},
        new int[]{1,6,1,16,32},
        new int[]{1,4,7,12,62},
        new int[]{1,6,1,0,64},
        new int[]{1,7,2,0,48},
        new int[]{1,7,2,0,48},
        new int[]{1,7,2,0,48}
    };
    
    public static final int[][] ORE_GEN_DEFAULTS_TERRENE =
    {    
        new int[]{1,10,30,20,140},
        new int[]{1,6,50,0,80},
        new int[]{1,8,8,0,64},
        new int[]{1,8,8,20,70},        
        new int[]{1,3,40,0,128},
        new int[]{1,6,2,20,32},
        new int[]{1,6,2,32,64},
        new int[]{1,4,14,24,124},
        new int[]{1,6,2,0,128},
        new int[]{1,7,4,0,96},
        new int[]{1,7,4,0,96},
        new int[]{1,7,4,0,96}
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
