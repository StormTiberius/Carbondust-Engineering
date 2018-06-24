package cde.tropics;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentMineshaftRoom;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureMineshaftStart extends StructureStart
{
    public StructureMineshaftStart(World par1World, Random par2Random, int par3, int par4)
    {
        ComponentMineshaftRoom var5 = new ComponentMineshaftRoom(0, par2Random, (par3 << 4) + 2, (par4 << 4) + 2);
        this.components.add(var5);
        var5.buildComponent(var5, this.components, par2Random);
        this.updateBoundingBox();
        this.markAvailableHeight(par1World, par2Random, 10);
    }
    
    @Override
    protected void markAvailableHeight(World par1World, Random par2Random, int par3)
    {
        int var4 = 127 - par3;
        int var5 = this.boundingBox.getYSize() + 1;

        if (var5 < var4)
        {
            var5 += par2Random.nextInt(var4 - var5);
        }

        int var6 = var5 - this.boundingBox.maxY;
        this.boundingBox.offset(0, var6, 0);
        Iterator var7 = this.components.iterator();

        while (var7.hasNext())
        {
            StructureComponent var8 = (StructureComponent)var7.next();
            var8.getBoundingBox().offset(0, var6, 0);
        }
    }
}
