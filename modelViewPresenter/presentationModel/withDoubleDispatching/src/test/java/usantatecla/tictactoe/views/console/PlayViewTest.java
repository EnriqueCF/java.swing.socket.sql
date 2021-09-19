package usantatecla.tictactoe.views.console;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import usantatecla.tictactoe.controllers.PlayController;
import usantatecla.tictactoe.models.Session;
import usantatecla.tictactoe.types.Color;
import usantatecla.tictactoe.types.Coordinate;
import usantatecla.tictactoe.types.Error;
import usantatecla.utils.views.Console;

@ExtendWith(MockitoExtension.class)
class PlayViewTest {

    @Mock
    private Console console;

    private PlayController playController;
    
    @Spy
    private Session session;
    
    private PlayView playView;

    @BeforeEach
    public void beforeEach(){
        this.playView = new PlayView();
    }

    @Test
    void testGivenPlayViewWhenInteractThenIsWinner() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readInt(anyString())).thenReturn(1);
            when(this.playController.getColor(any(Coordinate.class))).thenReturn(Color.O);
            when(this.playController.getActiveColor()).thenReturn(Color.O);
            doReturn(true).when(this.playController).isTicTacToe();
            when(this.playController.getPutTokenError(any(Coordinate.class))).thenReturn(Error.NULL);
            this.playView.interact(this.playController);
            verify(this.playController).next();
            verify(this.console).writeln("O player: You win!!! :-)");
        }
    }
    
    @Test
    public void testGivenPlayMenuWhenPlayerExecuteThenIsWinner() {
    	try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readInt(anyString())).thenReturn(1);
            when(this.playController.getActiveColor()).thenReturn(Color.O);
            when(this.session.isTicTacToe()).thenReturn(true);
            this.playView.interact(this.playController);
            verify(this.console).writeln("O player: You win!!! :-)");
        }
    }

}
