package com.example.compartyapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Component.class}, version =1, exportSchema = false)
public abstract class ComponentDatabase extends RoomDatabase
{
    private static ComponentDatabase instance; //to make sure there is not multiple instances of the database, but rather just use this one

    public abstract ComponentDao componentDao();

    public static synchronized ComponentDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),ComponentDatabase.class, "component_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private ComponentDao componentDao;

        private PopulateDbAsyncTask(ComponentDatabase db)
        {
            componentDao = db.componentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            componentDao.insert(new Component("Intel Core i7 9700k", 1, "Only compatible with their 300-series chipset-based motherboards, the Core i7-9700k 3.6 GHz eight-core LGA 1151 Processor from Intel is designed for gaming, creating, and productivity. It has a base Clock speed of 3.6 GHz and comes with features such as Intel Optane memory Support, AES-NI encryption, Intel vPro technology, Intel txt, Intel device protection with boot guard, and Intel vt-d virtualization technology for directed I/O. With Intel Turbo Boost Max 3.0 technology, the maximum Turbo frequency this Processor can achieve is 4.9 GHz.", "Intel","https://www.amazon.ca/Intel-BX80684I79700K-Boxed-i7-9700K-Processor/dp/B07HHN6KBZ/ref=sr_1_1?dchild=1&keywords=8700k&qid=1604876158&sr=8-1",455.99,"cpu"));
            componentDao.insert(new Component("AMD Ryzen 5 3600",2,"AMD CPU 100 100000031box Ryzen 5 3600 6C 12T 4200MHz 36MB 65W AM4 Wraith Stealth.","AMD", "https://www.amazon.ca/AMD-Ryzen-3600-12-thread-processor/dp/B07STGGQ18/ref=sr_1_3?dchild=1&keywords=8700k&qid=1604876158&sr=8-3",294.99, "cpu"));
            componentDao.insert(new Component("Intel Core i7-10700K", 3,"Intel Core i7-10700K Desktop Processor 8 Cores up to 5.1 GHz Unlocked LGA1200 (Intel 400 Series Chipset) 125W (BX8070110700K)","Intel","https://www.amazon.ca/Intel-i7-10700K-Processor-Unlocked-BX8070110700K/dp/B086ML4XSB/ref=sr_1_4?dchild=1&keywords=8700k&qid=1604876158&sr=8-4",521.99,"cpu"));
            componentDao.insert(new Component("Gigabyte GeForce GT 1030", 4, "SMOOTH AND CRISP VISUAL: Equipped with a DVI-D and a HDMI port, the card can deliver beautiful and smooth 4K video playback.", "GIGABYTE","https://www.amazon.ca/GIGABYTE-GeForce-GV-N1030D5-2GL-Computer-Graphics/dp/B071DY2VJR/ref=sr_1_5?crid=BSCW1KJECEJF&dchild=1&keywords=graphics+card&qid=1604876710&sprefix=graphics%2Caps%2C164&sr=8-5",119.99,"gpu"));
            componentDao.insert(new Component("MSI Gaming Radeon RX 5600 XT Boost Clock",5,"Great gaming experiences are created by bending the rules. The all new RDNA powered Radeon RX 5600 series for exceptional performance and High-fidelity gaming. Take control with Radeon RX 5600 series and experience powerful, accelerated gaming customized for you. High-performance RDNA architecture was engineered to greatly enhance features like Radeon Image Sharpening, FidelityFX, and VR technologies3 for maximum performance and jaw-dropping gaming experiences.","MSI","https://www.amazon.ca/MSI-Radeon-RX-5600-XT/dp/B08CKDLN91/ref=sr_1_6?crid=BSCW1KJECEJF&dchild=1&keywords=graphics%2Bcard&qid=1604876710&sprefix=graphics%2Caps%2C164&sr=8-6&th=1",439.99,"gpu"));
            componentDao.insert(new Component("MSI Gaming Radeon RX 5500 XT Boost Clock", 6, "Great gaming experiences are created by bending the rules. The all new RDNA powered Radeon RX 5500 XT series for exceptional performance and high-fidelity gaming. Take control with Radeon RX 5500 XT series and experience powerful, accelerated gaming customized for you. High-Performance RDNA architecture was engineered to greatly enhance features like Radeon image sharpening, FidelityFX, and VR technologies for maximum performance and jaw-dropping gaming experiences.", "MSI", "https://www.amazon.ca/MSI-Gaming-RX-5500-XT/dp/B082G2XDLL/ref=sr_1_7?crid=BSCW1KJECEJF&dchild=1&keywords=graphics%2Bcard&qid=1604876710&sprefix=graphics%2Caps%2C164&sr=8-7&th=1", 281.37, "gpu"));
            componentDao.insert(new Component("Corsair Vengeance LPX 16GB (2x8GB) DDR4 DRAM 3200MHz C16",7,"Vengeance LPX memory is designed for high performance overclocking. The heat spreader is made of pure aluminum for faster heat dissipation, and the eight layer PCB helps manage heat and provides superior overclocking headroom. Each IC is individually screened for performance potential.","Corsair","https://www.amazon.ca/Corsair-Vengeance-3200MHz-Desktop-Memory/dp/B0143UM4TC/ref=sr_1_5?crid=1QRBZ3EDZF7C7&dchild=1&keywords=ddr4+ram&qid=1604876934&sprefix=ddr4%2Caps%2C193&sr=8-5", 89.99, "ram"));
            componentDao.insert(new Component("XPG Z1 DDR4 3200MHz (PC4 25600) 16GB (2x8GB) 288-Pin CL-16-20-20", 8, "Legendary XPG Z1 Memory Designed for high-performance task needs, XPG Z1 memory modules feature superior thermodynamic-efficient design that consumes 20% less power than previous version memory even when running at up to 4266 MHz speed. : Limited Lifetime.", "XPG", "https://www.amazon.ca/XPG-3200MHz-288-Pin-CL-16-20-20-AX4U320038G16A-DBZ/dp/B082391T7M/ref=sr_1_12?crid=1QRBZ3EDZF7C7&dchild=1&keywords=ddr4+ram&qid=1604876934&sprefix=ddr4%2Caps%2C193&sr=8-12", 79.99, "ram"));
            componentDao.insert(new Component("Corsair Vengeance RGB PRO 16GB (2x8GB) DDR4 3200MHz C16", 9, "CORSAIR VENGEANCE RGB PRO Series DDR4 memory lights up your PC with mesmerizing dynamic multi-zone RGB lighting, while delivering the best in DDR4 performance and stability. Every module boasts ten individually controlled RGB LEDs, while wire-free design makes installation simple.", "Corsair", "https://www.amazon.ca/Corsair-Vengeance-3200MHz-Desktop-Memory/dp/B07D1XCKWW/ref=sr_1_7?crid=1QRBZ3EDZF7C7&dchild=1&keywords=ddr4+ram&qid=1604876934&sprefix=ddr4%2Caps%2C193&sr=8-7", 128.76, "ram"));
            componentDao.insert(new Component("Gigabyte B450M DS3H (AMD Ryzen AM4/Micro ATX/M.2/HMDI/DVI/USB 3.1/DDR4/Motherboard)", 10, "Supports AMD 2nd Generation Ryzen/ Ryzen with Radeon Vega Graphics/ 1st Generation Ryzen Processors Dual Channel Non ECC Unbuffered DDR4, 4 DIMMs HDMI, DVI D Ports for Multiple Display Ultra-Fast PCIe Gen3 x4 M.2 with PCIe NVMe and SATA mode support High Quality Audio Capacitors and Audio Noise Guard with LED Trace Path Lighting RGB Fusion supports RGB LED Strips in 7 Colors Realtek Gigabit LAN with cFosSpeed Internet Accelerator Software Smart Fan 5 Features 5 Temperature Sensors and 2 Hybrid Fan Headers with FAN STOP APP Center Including Easy Tune and Cloud Station Utilities CEC 2019 Ready, Save Power with a Single Click. Micro ATX Form Factor; 24.4 cm x 21.5 cm.","GIGABYTE", "https://www.amazon.ca/GIGABYTE-B450M-DS3H-Ryzen-Motherboard/dp/B07FWVJSHC/ref=sr_1_3?dchild=1&keywords=motherboard&qid=1604877096&sr=8-3",108.98,"motherboard"));
            componentDao.insert(new Component("MSI Performance Gaming AMD X470 Ryzen 2ND and 3rd Gen AM4 DDR4 DVI HDMI Onboard Graphics CFX ATX Motherboard",11,"Unlimited customization options and packed with exclusive gaming features, MSI performance gaming motherboards offer the best possible gaming experience. Customize and set up your own color scheme with MSI Mystic Light utility. Select the available colors from the palette to match your system style.","MSI","https://www.amazon.ca/MSI-Performance-Onboard-Graphics-Motherboard/dp/B07Y468HV3/ref=sr_1_5?dchild=1&keywords=motherboard&qid=1604877262&sr=8-5",175.64,"motherboard"));
            componentDao.insert(new Component("Asus ROG Strix B450-F Gaming Motherboard (ATX) AMD Ryzen 2 AM4 DDR4 DP HDMI M.2 USB 3.1 Gen2 B450", 12, "Experience next level performance with the ROG Strix B450 F Gaming, powered by 2nd generation AMD Ryzen AM4 processors. Unlock your system's potential with 5 Way Optimization featuring Fan Xpert 4 while personalizing your build with Aura Sync RGB. Integrated NVMe M.2, gigabit LAN and USB 3.1 Gen2 deliver maximum connectivity speeds.", "ASUS", "https://www.amazon.ca/ROG-Strix-B450-F-Gaming-Motherboard/dp/B07FKTZC4M/ref=sr_1_4?dchild=1&keywords=motherboard&qid=1604877096&sr=8-4",174.99,"motherboard"));
            componentDao.insert(new Component("NZXT H510 Compact Atx Mid-Tower PC Gaming Case, Front I/O USB C Port, Tempered Glass Side Panel, Cable Management System",13,"We’ve made our iconic H Series PC cases even better. Our new lineup still features the elements builders loved in the original H Series, including our patented cable management system, removable fan/radiator mounting brackets, and easy-to-use drive trays, alongside new updates like a front-panel USB-C connector supporting high-Speed USB 3.1 Gen 2 devices, a tempered glass side panel that installs with a single thumbscrew, and an upgraded Smart Device V2 on the H510i.", "NZXT", "https://www.amazon.ca/NZXT-H510-Compact-Mid-Tower-Tempered/dp/B07TC76671/ref=sr_1_1?dchild=1&keywords=pc+case&qid=1604877501&sr=8-1",99.99,"case"));
            componentDao.insert(new Component("Corsair Carbide Series Spec-DELTA RGB Tempered Glass Mid-Tower ATX Gaming Case Black Cases CC-9011166-WW, Temprered Glass",14,"The carbide series spec-delta RGB is a tempered glass mid-tower ATX case with striking angular styling, powerful airflow and vibrant RGB lighting. Direct airflow path design, inlaid mesh ventilation and four included cooling fans deliver incredible airflow to cool your system. An edge-to-edge tempered glass side panel window and smoked transparent front panel show off your system, while three 120mm RGB cooling fans light up your hardware.","Corsair", "https://www.amazon.ca/Corsair-Spec-Delta-Tempered-Mid-Tower-CC-9011166-WW/dp/B07KGSJRC3/ref=sr_1_2?dchild=1&keywords=pc+case&qid=1604877501&sr=8-2",99.99, "case"));
            componentDao.insert(new Component("DEEPCOOL MATREXX 55 V3 ADD-RGB 3F Case, E-ATX Supported, Motherboard or Button Control of SYNC of Addressable RGB Devices", 15, "A compact ATX computer case supports motherboards up to E-ATX.", "DEEPCOOL","https://www.amazon.ca/DEEPCOOL-MATREXX-Supported-Motherboard-Addressable/dp/B07ZNFWDPC/ref=sr_1_3?dchild=1&keywords=pc+case&qid=1604877501&sr=8-3", 104.99,"case"));
            componentDao.insert(new Component("Corsair CP-9020195-NA RM Series RM750 80 Plus Gold Fully Modular ATX Power Supply",16, "Corsair RM series power supplies are built with the highest quality components to deliver 80 Plus Gold efficient power to your PC. Rm PSUs utilize industrial-grade, 105 Degreec-rated capacitors for superb performance and reliability, backed by a ten-year warranty.","Corsair","https://www.amazon.ca/Corsair-CP-9020195-NA-RM750-Modular-Supply/dp/B07RF237B1/ref=sr_1_6?dchild=1&keywords=power+supply&qid=1604877630&sr=8-6",154.99,"powerSupply"));
            componentDao.insert(new Component("Corsair CP-9020179-NA RMX Series RM750x 80 Plus Gold Fully Modular ATX Power Supply", 17, "CORSAIR RM750x series power supplies are built with the highest quality components to deliver 80 PLUS Gold efficient power to your PC. Using only Japanese 105 DegreeC capacitors, users can depend on an RM750x PSUs' long life and reliability, backed by a ten-year warranty.","Corsair","https://www.amazon.ca/Corsair-CP-9020179-NA-RM750x-Modular-Supply/dp/B079HGN5QS/ref=sr_1_7?dchild=1&keywords=power+supply&qid=1604877630&sr=8-7",169.99,"powerSupply"));
            componentDao.insert(new Component("EVGA 650 GQ, 80+ Gold 650W, Semi Modular, EVGA ECO Mode, 5 Year Warranty, Power Supply 210-GQ-0650-V1",18,"Introducing the latest in the EVGA power supply line-up; the GQ series. These power supplies take some of the best features from EVGA's award winning power supplies, like EVGA ECO fan mode for near silent operation, Japanese Capacitor Design and a highly efficient design, at an excellent value. These new power supplies are 80 Plus Gold rated and offer an outstanding 5 Year Warranty that's backed by world class EVGA support.","EVGA","https://www.amazon.ca/EVGA-Modular-Warranty-Supply-210-GQ-0650-V1/dp/B017HA3SQ8/ref=sr_1_6?dchild=1&keywords=power%2Bsupply&qid=1604877630&sr=8-6&th=1",145.70,"powerSupply"));
            return null;
        }
    }
}
