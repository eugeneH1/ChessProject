package core;

import org.lwjgl.opengl.GL20;

public class ShaderManager {
    private final int programID;
    private int vertexShaderID, fragmentShaderID;

    public ShaderManager() throws Exception {
        programID = GL20.glCreateProgram();
        if(programID == 0)
            throw new Exception("Could not create shader");
    }

    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderID = createShader(shaderCode, GL20.GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderID = createShader(shaderCode, GL20.GL_FRAGMENT_SHADER);
    }

    public int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderID = GL20.glCreateShader(shaderType);
        if(shaderID == 0)
            throw new Exception("Error creating shader.\nType: " + shaderType);
        GL20.glShaderSource(shaderID, shaderCode);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling shader.\n" + shaderType + ": " + GL20.glGetShaderInfoLog(shaderID, 1024));
        }
        GL20.glAttachShader(programID, shaderID);
        return shaderID;
    }

    public void link() throws Exception {
        GL20.glLinkProgram(programID);
        if(GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == 0)
            throw new Exception("Error linking shader code.\n " +"Info log: " + GL20.glGetProgramInfoLog(programID, 1024));
        if(vertexShaderID != 0) {
            GL20.glDetachShader(programID, vertexShaderID);
        }
        if(fragmentShaderID != 0)
            GL20.glDetachShader(programID, fragmentShaderID);

        GL20.glValidateProgram(programID);
        System.out.println("ProgramID: " + programID);
        System.out.println("lgGetProgrami: " + GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS));
        if(GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == 0) {
            System.out.println(GL20.glGetProgramInfoLog(programID, 1024));
            //throw new Exception("Unable to validate shader code" + "\n" + GL20.glGetProgramInfoLog(programID, 1024));
        }
    }
    public void bind() {
        GL20.glUseProgram(programID);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void cleanUp() {
        unbind();
        if(programID != 0)
            GL20.glDeleteProgram(programID);
    }
}
