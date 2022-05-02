package net.logaa.robotiks.api;

public class MultiblockConfirmedFacing {
    public boolean confirmation;
    public String originFacing;
    public MultiblockConfirmedFacing(boolean confirmation, String originFacing)
    {
        this.confirmation = confirmation;
        this.originFacing = originFacing;
    }
}
