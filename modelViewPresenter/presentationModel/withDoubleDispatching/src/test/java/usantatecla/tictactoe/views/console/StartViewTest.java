package usantatecla.tictactoe.views.console;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import usantatecla.tictactoe.controllers.StartController;
import usantatecla.tictactoe.models.Session;
import usantatecla.utils.views.Console;

@ExtendWith(MockitoExtension.class)
public class StartViewTest {

    @Mock
    private Console console;

    private StartController startController;
    private StartView startView;
    private Conversor conversor;

    @BeforeEach
    public void beforeEach() {
        this.startController = new StartController(new Session());
        this.startView = new StartView();
        this.conversor = new Conversor();
    }

    @Test
    public void testGivenStartViewWhenInteractThenInteract() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            this.startView.interact(this.startController);
            String string = this.conversor.arrayToString(new String[]{
                    "--- TIC TAC TOE ---",
                    "---------------",
                    " |   |   |   | ",
                    " |   |   |   | ",
                    " |   |   |   | ",
                    "---------------"
            });
            ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
            verify(this.console, atLeast(0)).writeln(argumentCaptor.capture());
            verify(this.console, atLeast(0)).write(argumentCaptor.capture());
            List<String> argumentCaptorValues = argumentCaptor.getAllValues();
            this.conversor.reorder(argumentCaptorValues);
            assertThat(string, is(this.conversor.arrayToString(argumentCaptorValues.toArray())));
        }
    }

}
