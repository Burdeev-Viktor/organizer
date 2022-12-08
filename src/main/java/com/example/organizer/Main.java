package com.example.organizer;

import com.example.organizer.Service.ReminderService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.burningwave.core.assembler.StaticComponentContainer.Modules;

@SuppressWarnings("unchecked")
public class Main extends Application {
    public static void main(String[] args) {
        execute();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        //long in = ReminderService.getMilSeconds("23:02","23:01");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle(Const.TITLE_SIGN_IN);
        stage.setScene(scene);
        stage.show();

    }
    public static void execute() {
        try {
            Modules.exportAllToAll();
            Class<?> bootClassLoaderClass = Class.forName("jdk.internal.loader.ClassLoaders$BootClassLoader");
            Constructor<? extends ClassLoader> constructor =
                    (Constructor<? extends ClassLoader>)
                            Class.forName("jdk.internal.loader.ClassLoaders$PlatformClassLoader")
                                    .getDeclaredConstructor(bootClassLoaderClass);
            constructor.setAccessible(true);
            Class<?> classLoadersClass = Class.forName("jdk.internal.loader.ClassLoaders");
            Method bootClassLoaderRetriever = classLoadersClass.getDeclaredMethod("bootLoader");
            bootClassLoaderRetriever.setAccessible(true);
            ClassLoader newBuiltinclassLoader = constructor.newInstance(bootClassLoaderRetriever.invoke(classLoadersClass));

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}