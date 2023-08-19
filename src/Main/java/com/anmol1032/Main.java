package com.anmol1032;

import com.anmol1032.data.HouseData;
import com.anmol1032.data.Tenant;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Vector;

public class Main extends JFrame {
    static Color bg = new Color(7, 86, 73);
    static Color fg = new Color(213, 255, 244);
    SaveData saveData;
    JPanel cp;

    public Main() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            saveData = new SaveData();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        cp = new JPanel();
        cp.setBackground(bg);

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setTitle("Tenant Management");
        setPreferredSize(new Dimension(400, 500));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setContentPane(cp);
        pack();
        setVisible(true);

        MetalLookAndFeel.setCurrentTheme(new Theme());

        showData();
        invalidate();
        validate();
        repaint();
    }

    public static void main(String[] args) {
        /*System.out.println("-----------");
        SaveData saveData = null;
        try {
            saveData = new SaveData();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println(saveData.data);

        saveData.data.add(new HouseData("house2"));

        saveData.data.find("house2").add(new Tenant("a", 100));
        saveData.data.find("house2").add(new Tenant("b", 200));
        saveData.data.find("house2").add(new Tenant("c", 300));
        saveData.data.find("house2").add(new Tenant("d", 300));
        saveData.data.find("house2").add(new Tenant("e", 300));
        saveData.data.find("house2").add(new Tenant("f", 300));
        saveData.data.find("house2").add(new Tenant("anmol", 300));
        saveData.data.find("house2").add(new Tenant("abc", 300));
        saveData.data.find("house2").add(new Tenant("asdf", 300));
        saveData.data.find("house2").add(new Tenant("asdf2", 300));
        saveData.data.find("house2").add(new Tenant("asdf3", 300));
        saveData.data.find("house2").add(new Tenant("num", 300));
        saveData.data.find("house2").add(new Tenant("num1230", 300));
        saveData.data.find("house2").add(new Tenant("350afd0", 300));
        saveData.data.find("house2").add(new Tenant("350afd0", 300));
        saveData.data.find("house2").add(new Tenant("a5df300", 300));
        saveData.data.find("house2").add(new Tenant("35fadd00", 300));
        saveData.data.find("house2").add(new Tenant("350fd0", 300));
        saveData.data.find("house2").add(new Tenant("35asfd00", 300));
        saveData.data.find("house2").add(new Tenant("350dfa0", 300));
        saveData.data.find("house2").add(new Tenant("350da0", 300));
        saveData.data.find("house2").add(new Tenant("30adf0", 300));
        saveData.data.find("house2").add(new Tenant("30adf0", 300));

        System.out.println(saveData.data);

        saveData.write();
*/

        Main main = new Main();

        main.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                main.saveData.write();
                ((JFrame) (e.getComponent())).dispose();
            }
        });
    }

    public void showData() {
        JPanel housePanel = new JPanel();
        housePanel.setBackground(bg);

        String[] h = new String[saveData.data.houses.size()];
        for (int i = 0; i < h.length; i++) {
            h[i] = saveData.data.houses.get(i).getName();
        }

        JComboBox<String> comboBox = new JComboBox<>(h);
        comboBox.setBackground(bg);
        comboBox.setForeground(fg);


        JButton jButton = new JButton("Select");
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                housePanel.setVisible(false);
                cp.remove(housePanel);
                showHouse(saveData.data.find(((String) comboBox.getSelectedItem())));
            }
        });
        
        JButton addHouseButton = new JButton("Add house");
        addHouseButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                housePanel.setVisible(false);
                cp.remove(housePanel);
                addHouse();
            }
        });
        

        housePanel.add(comboBox);
        housePanel.add(jButton);
        housePanel.add(addHouseButton);

        cp.add(housePanel);
        
    }

    private void addHouse() {
        JPanel housePanel = new JPanel(new GridBagLayout());
        housePanel.setBackground(bg);

        GridBagConstraints gbc = new GridBagConstraints();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                housePanel.setVisible(false);
                cp.remove(housePanel);
                showData();
            }
        });

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setForeground(fg);
        nameLabel.setFont(new Font("default", Font.PLAIN, getWidth() / 23));

        JTextField nameField = new JTextField(10);
        nameField.setForeground(fg);
        nameField.setCaretColor(fg);
        nameField.setBackground(bg.darker());
        nameField.setFont(new Font("default", Font.PLAIN, getWidth() / 23));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(nameField.getText(), "")) {
                    error("Name can't be empty");
                    return;
                }

                saveData.data.add(new HouseData(nameField.getText()));
                saveData.write();
                housePanel.setVisible(false);
                cp.remove(housePanel);
                showData();
            }
        });


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        housePanel.add(nameLabel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        housePanel.add(nameField, gbc);

        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        housePanel.add(addButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;
        housePanel.add(backButton, gbc);

        cp.add(housePanel);
    }

    private void showHouse(HouseData houseData) {
        JPanel tenantsPanel = new JPanel(new GridBagLayout());
        tenantsPanel.setBackground(bg);

        GridBagConstraints gbc = new GridBagConstraints();

        Vector<Vector<String>> a = new Vector<>();
        houseData.tenants.forEach(p -> {
            Vector<String> b = new Vector<>();
            b.add(p.getName());
            b.add(("" + p.getMoney()));
            a.add(b);
        });

        Vector<String> column = new Vector<>();
        column.add("NAME");
        column.add("To Pay");


        JTable table = new JTable(a, column);
        table.setRowHeight(getWidth() / 25);
        table.setFont(new Font("default", Font.PLAIN, getWidth() / 26));
        table.setDefaultEditor(Object.class, null);

        ListSelectionModel listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.setBackground(bg.brighter());
        table.setForeground(fg);

        JScrollPane sp = new JScrollPane(table);
        sp.getViewport().setBackground(bg);
        sp.setBackground(bg);
        sp.setForeground(fg);
        sp.setPreferredSize(new Dimension(getWidth() - 20, getHeight() - 100));

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() < 0) {
                    error("Nothing Selected");
                    return;
                }

                tenantsPanel.setVisible(false);
                cp.remove(tenantsPanel);
                showTenant(houseData.find(
                        ((String) table.getValueAt(table.getSelectedRow(), 0))), houseData);
            }
        });

        JButton addButton = new JButton("Add Tenant");
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tenantsPanel.setVisible(false);
                cp.remove(tenantsPanel);
                addTenant(houseData);
            }
        });


        JButton backButton = new JButton("Back");
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tenantsPanel.setVisible(false);
                cp.remove(tenantsPanel);
                showData();
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        tenantsPanel.add(sp, gbc);

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        tenantsPanel.add(addButton, gbc);

        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        tenantsPanel.add(selectButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        tenantsPanel.add(backButton, gbc);

        cp.add(tenantsPanel);
    }

    private void addTenant(HouseData houseData) {
        JPanel tenantPanel = new JPanel(new GridBagLayout());
        tenantPanel.setBackground(bg);

        GridBagConstraints gbc = new GridBagConstraints();


        JLabel errorLabel = new JLabel("Name or Money per Month can't be empty");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("default", Font.ITALIC, getWidth() / 28));
        errorLabel.setVisible(false);

        JLabel errorLabel2 = new JLabel("Money per Month must only contain number.");
        errorLabel2.setForeground(Color.RED);
        errorLabel2.setFont(new Font("default", Font.ITALIC, getWidth() / 28));
        errorLabel2.setVisible(false);


        JButton backButton = new JButton("Back");
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tenantPanel.setVisible(false);
                cp.remove(tenantPanel);
                showHouse(houseData);
            }
        });

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setForeground(fg);
        nameLabel.setFont(new Font("default", Font.PLAIN, getWidth() / 23));

        JLabel moneyLabel = new JLabel("Money per Month: ");
        moneyLabel.setForeground(fg);
        moneyLabel.setFont(new Font("default", Font.PLAIN, getWidth() / 24));


        JTextField nameField = new JTextField(10);
        nameField.setForeground(fg);
        nameField.setCaretColor(fg);
        nameField.setBackground(bg.darker());
        nameField.setFont(new Font("default", Font.PLAIN, getWidth() / 23));

        JTextField moneyField = new JTextField(10);
        moneyField.setAlignmentX(0f);
        moneyField.setForeground(fg);
        moneyField.setCaretColor(fg);
        moneyField.setBackground(bg.darker());
        moneyField.setFont(new Font("default", Font.BOLD, getWidth() / 24));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(nameField.getText(), "") || Objects.equals(moneyField.getText(), "")) {
                    errorLabel.setVisible(true);
                    return;
                } else if (!moneyField.getText().matches("^[0-9]*$")) {
                    errorLabel.setVisible(false);
                    errorLabel2.setVisible(true);
                    return;
                }

                houseData.add(new Tenant(nameField.getText(), Long.parseLong(moneyField.getText())));
                saveData.write();
                tenantPanel.setVisible(false);
                cp.remove(tenantPanel);
                showHouse(houseData);
            }
        });


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        tenantPanel.add(nameLabel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        tenantPanel.add(nameField, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        tenantPanel.add(moneyLabel, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        tenantPanel.add(moneyField, gbc);

        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        tenantPanel.add(addButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;
        tenantPanel.add(backButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 10;
        tenantPanel.add(errorLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 12;
        tenantPanel.add(errorLabel2, gbc);

        cp.add(tenantPanel);
    }

    private void showTenant(Tenant tenant, HouseData houseData) {
        JPanel tenantPanel = new JPanel(new GridBagLayout());
        tenantPanel.setBackground(bg);

        GridBagConstraints gbc = new GridBagConstraints();


        JButton backButton = new JButton("Back");
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tenantPanel.setVisible(false);
                cp.remove(tenantPanel);
                showHouse(houseData);
            }
        });

        JLabel nameLabel = new JLabel(tenant.getName(), SwingConstants.CENTER);
        nameLabel.setForeground(fg);
        nameLabel.setFont(new Font("default", Font.BOLD, getWidth() / 7));

        JLabel moneyLabel = new JLabel("Money remaining: " + tenant.getMoney());
        moneyLabel.setForeground(fg);
        moneyLabel.setFont(new Font("default", Font.BOLD, getWidth() / 18));

        JLabel mpmLabel = new JLabel("Money per month: " + tenant.getMpM());
        mpmLabel.setForeground(fg);
        mpmLabel.setFont(new Font("default", Font.BOLD, getWidth() / 18));


        JLabel payedLabel = new JLabel("Payed: ");
        payedLabel.setForeground(fg);
        payedLabel.setFont(new Font("default", Font.PLAIN, getWidth() / 24));


        JTextField payedField = new JTextField(10);
        payedField.setForeground(fg);
        payedField.setCaretColor(fg);
        payedField.setBackground(bg.darker());
        payedField.setFont(new Font("default", Font.PLAIN, getWidth() / 23));

        JButton payedButton = new JButton("Done");
        payedButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tenant.payed(Long.parseLong(payedField.getText()));
                } catch (NumberFormatException ex) {
                    error("Field show be of number.");
                }
                payedField.setText("0");
                tenantPanel.setVisible(false);
                cp.remove(tenantPanel);
                showTenant(tenant, houseData);
            }
        });

        JLabel infoLabel = new JLabel("Use Negative for increasing money.");
        infoLabel.setFont(new Font("default", Font.PLAIN, getWidth() / 24));
        System.out.println(getWidth());
        infoLabel.setForeground(fg);


        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        tenantPanel.add(nameLabel, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 3;
        tenantPanel.add(moneyLabel, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 4;
        tenantPanel.add(mpmLabel, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 5;
        tenantPanel.add(infoLabel, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 6;
        tenantPanel.add(payedLabel, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 6;
        tenantPanel.add(payedField, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 6;
        tenantPanel.add(payedButton, gbc);

        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 16;
        tenantPanel.add(backButton, gbc);

        cp.add(tenantPanel);
    }

    public void error(String s) {
        JFrame jFrame = new JFrame("Error");
        jFrame.getContentPane().add(
                new JLabel(s)
        );

        jFrame.setUndecorated(true);
        jFrame.getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
        jFrame.setPreferredSize(new Dimension(getWidth() / 3, getHeight() / 3));
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(this);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

