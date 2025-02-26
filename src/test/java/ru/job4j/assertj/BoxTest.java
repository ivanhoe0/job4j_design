package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void whenUnknown() {
        Box box = new Box(42, -12);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void whenTetrahedronThen4Vertex() {
        Box box = new Box(4, 4);
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
    }

    @Test
    void whenSphereThen0Vertex() {
        Box box = new Box(0, 33);
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
    }

    @Test
    void whenExist() {
        Box box = new Box(8, 12);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenDoesNotExist() {
        Box box = new Box(2, 10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenEdge0ThenDoNotExist() {
        Box box = new Box(0, 0);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenTetrahedronWithEdge12() {
        Box box = new Box(4, 12);
        double area = box.getArea();
        assertThat(area).isEqualTo(249.4153, withPrecision(0.0001));
    }

    @Test
    void whenSphereWithRadius5() {
        Box box = new Box(0, 5);
        double area = box.getArea();
        assertThat(area).isCloseTo(314.159, withPrecision(0.001));
    }
}