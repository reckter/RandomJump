package me.reckter.Modifier;

/**
 * Created with IntelliJ IDEA.
 * User: mediacenter
 * Date: 03.10.13
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class CastSpeedModifier extends BaseModifier {
    public CastSpeedModifier(int timeToLive, float flatModifier, float percentageModifier) {
        super(timeToLive, flatModifier, percentageModifier);
    }

    @Override
    public int castTime(int castTime) {
        return (int) addModifier(castTime);
    }
}
