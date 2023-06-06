package tools.redstone.redstonetools.features.commands;

import com.google.auto.service.AutoService;
import net.minecraft.server.command.ServerCommandSource;
import tools.redstone.redstonetools.features.AbstractFeature;
import tools.redstone.redstonetools.features.Feature;
import tools.redstone.redstonetools.features.arguments.Argument;
import tools.redstone.redstonetools.features.feedback.Feedback;

import java.math.BigInteger;

import static tools.redstone.redstonetools.features.arguments.serializers.BigIntegerSerializer.bigInteger;
import static tools.redstone.redstonetools.features.arguments.serializers.NumberBaseSerializer.numberBase;

@AutoService(AbstractFeature.class)
@Feature(name = "Base Convert", description = "Converts a number from one base to another.", command = "base")
public class BaseConvertFeature extends CommandFeature {

    public static final Argument<BigInteger> number = Argument
            .ofType(bigInteger());
    public static final Argument<Integer> toBase = Argument
            .ofType(numberBase())
            .withDefault(10);

    @Override
    protected Feedback execute(ServerCommandSource source) {
        var output = number.getValue().toString(toBase.getValue());

        if (toBase.getValue() == 2)
            output = "0b" + number.getValue().toString(toBase.getValue());
        else if (toBase.getValue() == 8)
            output = "0o" + number.getValue().toString(toBase.getValue());
        else if (toBase.getValue() == 10)
            output = "0d" + number.getValue().toString(toBase.getValue());
        else if (toBase.getValue() == 16)
            output = "0x" + number.getValue().toString(toBase.getValue());
        else
            output = "Base " + toBase.getValue().toString() + ": " + number.getValue().toString(toBase.getValue());

        return Feedback.success(output);
    }
}
